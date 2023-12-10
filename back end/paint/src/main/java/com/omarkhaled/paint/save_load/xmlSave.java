package com.omarkhaled.paint.save_load;
import com.omarkhaled.paint.shape.ShapeService;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class xmlSave {

    ShapeService shapeService;

    public xmlSave(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    //custom save
    public void save(Path path){
        try {
            FileOutputStream fos = new FileOutputStream(path.toFile());
            XMLEncoder encoder = new XMLEncoder(fos);
            for (int i = 0; i < shapeService.getList().size(); i++){
                encoder.writeObject(shapeService.getList().get(i));
            }
            encoder.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("shapes not saved");
        }

    }
}
