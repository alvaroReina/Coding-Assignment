package rectangles.adapters;

import com.google.gson.annotations.SerializedName;
import rectangles.entities.Rectangle;

import java.util.List;

public class RectangleListAdapter {
    @SerializedName("rects")
    private List<Rectangle> rectangleList;

    public List<Rectangle> getRectangleList() {
        return rectangleList;
    }

    public void setRectangleList(List<Rectangle> rectangleList) {
        this.rectangleList = rectangleList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Rectangle rectangle : rectangleList) {
            sb.append("\t" + rectangle + "\n");
        }
        return sb.toString();
    }
}
