package com.example.andrew.myapplication;

import android.graphics.Point;
import android.graphics.Rect;

import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

import java.util.List;

public class ModifiedTextBlock {
    private Point[] cornerPoints;
    private String string;
    private List<? extends Text> textComponents;
    private Rect rect;

    public ModifiedTextBlock(TextBlock tb) {
        this.cornerPoints = tb.getCornerPoints();
        this.string = tb.getValue();
        this.textComponents = tb.getComponents();
        this.rect = tb.getBoundingBox();
    }

    public Point[] getCornerPoints() {
        return cornerPoints;
    }

    public String getString() {
        return string;
    }

    public List<? extends Text> getComponents() {
        return textComponents;
    }

    public Rect getBoundingBox() {
        return rect;
    }

    public void appendString(String appended) {
        this.string = this.string + appended;
    }
}
