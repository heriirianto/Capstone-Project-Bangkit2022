package com.bangkit.herai_ai_basedwastebank.ml;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.metadata.MetadataExtractor;
import org.tensorflow.lite.support.model.Model;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/**
 * This model doesn't have metadata, so no javadoc can be generated. */
public final class WsmodelGAPQuant2 {
  @NonNull
  private final Model model;

  private WsmodelGAPQuant2(@NonNull Context context, @NonNull Model.Options options) throws
      IOException {
    model = Model.createModel(context, "wsmodelGAPQuant2.tflite", options);
    MetadataExtractor extractor = new MetadataExtractor(model.getData());
  }

  @NonNull
  public static WsmodelGAPQuant2 newInstance(@NonNull Context context) throws IOException {
    return new WsmodelGAPQuant2(context, (new Model.Options.Builder()).build());
  }

  @NonNull
  public static WsmodelGAPQuant2 newInstance(@NonNull Context context,
      @NonNull Model.Options options) throws IOException {
    return new WsmodelGAPQuant2(context, options);
  }

  @NonNull
  public Outputs process(@NonNull TensorBuffer inputFeature0) {
    TensorBuffer processedinputFeature0 = inputFeature0;
    Outputs outputs = new Outputs(model);
    model.run(new Object[] {processedinputFeature0.getBuffer()}, outputs.getBuffer());
    return outputs;
  }

  public void close() {
    model.close();
  }

  public class Outputs {
    private TensorBuffer outputFeature0;

    private Outputs(Model model) {
      this.outputFeature0 = TensorBuffer.createFixedSize(model.getOutputTensorShape(0), DataType.FLOAT32);
    }

    @NonNull
    public TensorBuffer getOutputFeature0AsTensorBuffer() {
      return outputFeature0;
    }

    @NonNull
    private Map<Integer, Object> getBuffer() {
      Map<Integer, Object> outputs = new HashMap<>();
      outputs.put(0, outputFeature0.getBuffer());
      return outputs;
    }
  }
}
