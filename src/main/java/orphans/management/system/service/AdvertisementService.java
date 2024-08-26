package orphans.management.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import orphans.management.system.constant.FileConstant;
import orphans.management.system.model.Advertisement;
import orphans.management.system.model.User;
import orphans.management.system.repository.AdvertisementRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final UserService userService;



    public Advertisement createAdvertisement(String title, String description, MultipartFile image, long userId) throws IOException {
        Advertisement advertisement = new Advertisement();
        User user = userService.findUserByUserId(userId);
        advertisement.setTitle(title);
        advertisement.setDescription(description);
        advertisement.setCreatedDate(new Date());
        advertisement.setUser(user);
        advertisementRepository.save(advertisement);
        saveAdvertisementImage(advertisement, image);
        return advertisement;
    }


    private void saveAdvertisementImage(Advertisement advertisement, MultipartFile image) throws IOException {
        if (image != null) {
            Path advertisementFolder = Paths.get(FileConstant.ADVERTISEMENT_FOLDER + advertisement.getId()).toAbsolutePath().normalize();
            if (!Files.exists(advertisementFolder)) {
                Files.createDirectories(advertisementFolder);
                log.info("Directory created for " + advertisementFolder);
            }

            Files.deleteIfExists(Paths.get(FileConstant.ADVERTISEMENT_FOLDER + advertisement.getId() + "." + "jpg"));
            Files.copy(image.getInputStream(), advertisementFolder.resolve(advertisement.getId() + "." + "jpg"), REPLACE_EXISTING);
            advertisement.setImage(setAdvertisementImage(advertisement.getId()));
            advertisementRepository.save(advertisement);
            log.info(image.getOriginalFilename());
        }
    }

    private String setAdvertisementImage(long bookAuthorId) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.ADVERTISEMENT_IMAGE_PATH + bookAuthorId + "/" + bookAuthorId + "." + "jpg").toUriString();
    }


    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }


    public Advertisement updateAdvertisement(String title, String description, MultipartFile image, long bookId) throws IOException {
        Advertisement advertisement = findAdvertisement(bookId);
        advertisement.setTitle(title);
        advertisement.setDescription(description);
        advertisementRepository.save(advertisement);
        saveAdvertisementImage(advertisement, image);
        return advertisement;
    }

    public Advertisement findAdvertisement(long id) {
        Advertisement advertisement = advertisementRepository.findAdvertisementById(id);
        if (advertisement == null) {
            throw new RuntimeException("Advertisement author not found");
        }

        return advertisement;
    }


    public void deleteAdvertisement(long id) {
        Advertisement advertisement = findAdvertisement(id);
        advertisementRepository.deleteById(advertisement.getId());
    }
}
