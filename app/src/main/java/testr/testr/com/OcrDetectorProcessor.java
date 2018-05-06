package testr.testr.com;

import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import java.util.ArrayList;

public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> mGraphicOverlay;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        mGraphicOverlay = ocrGraphicOverlay;
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(1, "A"));
        questions.add(new Question(2, "B"));
        questions.add(new Question(3, "C"));
        questions.add(new Question(4, "D"));

        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            ModifiedTextBlock mtb = new ModifiedTextBlock(item);
            //mtb.appendString("stasdsfasdfsd");
            OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, mtb);
            mGraphicOverlay.add(graphic);
        }
    }

    @Override
    public void release() {
        mGraphicOverlay.clear();
    }
}
