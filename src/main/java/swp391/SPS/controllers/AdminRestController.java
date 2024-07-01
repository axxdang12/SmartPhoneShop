package swp391.SPS.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp391.SPS.dtos.PageDto;
import swp391.SPS.dtos.RequestSaveActiveDto;
import swp391.SPS.dtos.RequestSaveUserRoleDto;
import swp391.SPS.entities.User;
import swp391.SPS.exceptions.NoDataInListException;
import swp391.SPS.exceptions.OutOfPageException;
import swp391.SPS.exceptions.UserNotFoundException;
import swp391.SPS.services.RoleService;
import swp391.SPS.services.UserService;

@RestController
@AllArgsConstructor
public class AdminRestController {
    private UserService userService;

    private RoleService roleService;

    @PostMapping("/save-role/")
    public ResponseEntity saveRole(@RequestBody RequestSaveUserRoleDto requestSaveUserRoleDto) throws NoDataInListException, OutOfPageException, UserNotFoundException {
        User user = userService.saveUserRole(requestSaveUserRoleDto.getUserId(), requestSaveUserRoleDto.getRoleName());
        PageDto pageDto = userService.getListUserFirstLoad(requestSaveUserRoleDto.getPage() - 1, 2);
        return ResponseEntity.ok(PageDto.builder().resultList(pageDto.getResultList()).
                roles(roleService.findAll()).
                currentPage(pageDto.getCurrentPage()).
                totalPage(pageDto.getTotalPage()).
                size(pageDto.getSize()).
                build());
    }

    @PostMapping("/save-active/")
    public ResponseEntity saveActive(@RequestBody RequestSaveActiveDto requestSaveActiveDto) throws UserNotFoundException, NoDataInListException, OutOfPageException {
        userService.saveUserActive(requestSaveActiveDto.getUserId(), requestSaveActiveDto.getStatus());
        PageDto pageDto = userService.getListUserFirstLoad(requestSaveActiveDto.getPage() - 1, 2);
        return ResponseEntity.ok(PageDto.builder().resultList(pageDto.getResultList()).
                roles(roleService.findAll()).
                currentPage(pageDto.getCurrentPage()).
                totalPage(pageDto.getTotalPage()).
                size(pageDto.getSize()).
                build());
    }
}
