package swp391.SPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import swp391.SPS.dtos.PageDto;
import swp391.SPS.dtos.RequestSaveUserRoleDto;
import swp391.SPS.exceptions.NoDataInListException;
import swp391.SPS.exceptions.OutOfPageException;
import swp391.SPS.repositories.RoleRepository;
import swp391.SPS.services.RoleService;
import swp391.SPS.services.UserService;

@RestController
public class AdminController {
    @Autowired private UserService userService;

    @Autowired private RoleService roleService;

    @GetMapping(value = "/admin-dashboard/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity adminDashBoardPage(@PathVariable(name = "page") int page,
                                             @PathVariable(name = "size") int size
    ) throws NoDataInListException, OutOfPageException {
        return userService.getListUser(page, size);
    }

    @PostMapping("/save-role/{page}/{size}")
    public ResponseEntity getListAfterSaveRole(@PathVariable(name = "page") int page,@PathVariable(name = "size") int size,
    @RequestBody RequestSaveUserRoleDto requestSaveUserRoleDto
    ) throws NoDataInListException {
        userService.saveUserRole(requestSaveUserRoleDto.getUserId(), requestSaveUserRoleDto.getRoleName());
        return userService.getListUser(page,size);
    }
}
