package orphans.management.system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import orphans.management.system.constant.FileConstant;
import orphans.management.system.model.Advertisement;
import orphans.management.system.service.AdvertisementService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @PostMapping("/create/user/{userId}")
    public ResponseEntity<Advertisement> createAdvertisement(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @PathVariable long userId) throws IOException {

        Advertisement advertisement = advertisementService.createAdvertisement(title, description, image, userId);
        return new ResponseEntity<>(advertisement, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Advertisement> updateAdvertisement(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @PathVariable long id) throws IOException {

        Advertisement advertisement = advertisementService.updateAdvertisement(title, description, image, id);
        return new ResponseEntity<>(advertisement, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){
        List<Advertisement> allAdvertisements = advertisementService.getAllAdvertisements();
        return new ResponseEntity<>(allAdvertisements, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Advertisement> findAdvertisement(@PathVariable long id){
        Advertisement advertisement = advertisementService.findAdvertisement(id);
        return new ResponseEntity<>(advertisement, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdvertisement(@PathVariable long id){
        advertisementService.deleteAdvertisement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //    display room image
    @GetMapping(path = "/image/{id}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable String id, @PathVariable String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(FileConstant.ADVERTISEMENT_FOLDER + id + "/" + fileName));
    }

}
