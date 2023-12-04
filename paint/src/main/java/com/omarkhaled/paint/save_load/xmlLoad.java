package com.omarkhaled.paint.save_load;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.omarkhaled.paint.shape.Shape;
import com.omarkhaled.paint.shape.ShapeService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public class xmlLoad {
    ShapeService shapeService;

    public xmlLoad(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    public void load(Path path){

        try {
            XmlMapper mapper = new XmlMapper();
            InputStream inputStream = new FileInputStream(path.toFile());
            TypeReference<List<Shape>> typeReference = new TypeReference<List<Shape>>() {};
            List<Shape> list = mapper.readValue(inputStream, typeReference);
            shapeService.setList(list);
//            studentService = mapper.readValue(path.toFile(), StudentService.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
