package com.omarkhaled.paint.save_load;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.omarkhaled.paint.shape.ShapeService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class xmlSave {

    ShapeService shapeService;

    public xmlSave(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    public void save(Path path){

        try {
            ObjectMapper mapper = new XmlMapper();
            mapper.writeValue(path.toFile(), shapeService.getList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
