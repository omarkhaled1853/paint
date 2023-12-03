package com.omarkhaled.paint.shape;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShapeService {
    private List<Shape> list;
//    private List<Shape> list;

    public List<Shape> getList() {
        return list;
    }

    public void setList(List<Shape> list) {
        this.list = list;
    }
}
