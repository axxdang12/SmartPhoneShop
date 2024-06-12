package swp391.SPS.services;

import swp391.SPS.entities.Picture;

public interface PictureService {
    public void addPicture(Picture p);
    public void editPircure(Picture p);
    public void deletePicture(Picture p);
    public Picture getPictureById(int p);
}
