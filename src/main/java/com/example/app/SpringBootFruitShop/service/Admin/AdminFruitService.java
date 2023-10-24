package com.example.app.SpringBootFruitShop.service.Admin;

import com.example.app.SpringBootFruitShop.entity.Fruit;
import com.example.app.SpringBootFruitShop.exceptions.UseOrderException;
import com.example.app.SpringBootFruitShop.repository.FruitRepository;
import com.example.app.SpringBootFruitShop.utils.Constants;
import com.example.app.SpringBootFruitShop.utils.ResponseMessage;
import com.example.app.SpringBootFruitShop.utils.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

@Service
public class AdminFruitService {

    private static final Logger LOGGER =
            Logger.getLogger(AdminFruitService.class.getName());

    @Autowired
    FruitRepository repository;

    public ResponseEntity<?> addFruit(String[] data, MultipartFile multipartFile) throws IOException {
        Map<String, String> errors = validateData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UseOrderException("Check inputs", errors);
            } catch (UseOrderException e) {
                return new ResponseEntity<>(new ResponseMessage(false,
                        e.getErrors(errors)), HttpStatus.OK);
            }
        } else {
            String imgPrefix = StringGenerator.genStr();
            Fruit fruit = new Fruit();
            fruit.setName(data[0]);
            fruit.setArticle(data[1]);
            fruit.setDescr(data[2]);
            fruit.setPrice(Double.parseDouble(data[3]));

            String fileUpload = imgPrefix + "-" + data[4];
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(Constants.URL_TO_UPLOADS + fileUpload);
            Files.write(path, bytes);

            LOGGER.info("Upload path info: " + path);

            fruit.setImg(fileUpload);
            repository.save(fruit);

            return new ResponseEntity<>(new ResponseMessage(true,
                    Constants.SAVED_MSG), HttpStatus.OK);
        }
    }

    public List<Fruit> getAll() {
        Iterable<Fruit> iterable = repository.findAll();
        List<Fruit> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(fruit -> new Fruit(fruit.getId(), fruit.getImg(),
                                fruit.getName(), fruit.getArticle(),
                                fruit.getDescr(), fruit.getPrice()))
                        .toList();
        return new ArrayList<>(list);
    }

    public ResponseEntity<?> updateFruit(String[] data, MultipartFile multipartFile, Long id) throws IOException {
        Optional<Fruit> optionalFruit = repository.findById(id);
        if (optionalFruit.isPresent()) {
            Fruit fruit = optionalFruit.get();
            Map<String, String> errors = validateData(data);
            if (!errors.isEmpty()) {
                try {
                    throw new UseOrderException("Check inputs", errors);
                } catch (UseOrderException e) {
                    return new ResponseEntity<>(new ResponseMessage(false, e.getErrors(errors)), HttpStatus.OK);
                }
            } else {
                // Обновляем данные фрукта
                fruit.setName(data[0]);
                fruit.setArticle(data[1]);
                fruit.setDescr(data[2]);
                fruit.setPrice(Double.parseDouble(data[3]));

                // Обновляем изображение, если было передано новое изображение
                if (multipartFile != null && !multipartFile.isEmpty()) {
                    String imgPrefix = StringGenerator.genStr();
                    String fileUpload = imgPrefix + "-" + data[4];
                    byte[] bytes = multipartFile.getBytes();
                    Path path = Paths.get(Constants.URL_TO_UPLOADS + fileUpload);
                    Files.write(path, bytes);
                    LOGGER.info("Upload path info: " + path);
                    fruit.setImg(fileUpload);
                }

                // Сохраняем обновленные данные в репозиторий
                repository.save(fruit);
                return new ResponseEntity<>(new ResponseMessage(true, Constants.UPDATED_MSG), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage(false, Constants.NOT_FOUND_MSG), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteFruit(Long id) {
        Optional<Fruit> optionalFruit = repository.findById(id);
        if (optionalFruit.isPresent()) {
            Fruit fruit = optionalFruit.get();
            repository.delete(fruit);
            return new ResponseEntity<>(new ResponseMessage(true, Constants.DELETED_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage(false, Constants.NOT_FOUND_MSG), HttpStatus.NOT_FOUND);
        }
    }

    private Map<String, String> validateData(String[] data) {
        Map<String, String> errors = new HashMap<>();
        if (data[0].isEmpty() || data[0].trim().isEmpty())
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (data[1].isEmpty() || data[1].trim().isEmpty())
            errors.put("article", Constants.INPUT_REQ_MSG);
        if (data[2].isEmpty() || data[2].trim().isEmpty())
            errors.put("description", Constants.INPUT_REQ_MSG);
        if (data[3].isEmpty() || data[3].trim().isEmpty())
            errors.put("price", Constants.INPUT_REQ_MSG);
        if (data[4].isEmpty() || data[4].trim().isEmpty())
            errors.put("file", Constants.ADD_FILE_MSG);
        return errors;
    }
}
