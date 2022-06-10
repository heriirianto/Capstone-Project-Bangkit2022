// Generated by view binder compiler. Do not edit!
package com.bangkit.herai_ai_basedwastebank.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.bangkit.herai_ai_basedwastebank.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGerobakBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnJualSampah;

  @NonNull
  public final RelativeLayout rlTotal;

  @NonNull
  public final RecyclerView rvGerobak;

  @NonNull
  public final TextView tvComp1;

  @NonNull
  public final TextView tvComp2;

  @NonNull
  public final TextView tvTitlePage;

  @NonNull
  public final TextView tvTotalBerat;

  @NonNull
  public final TextView tvTotalHarga;

  private ActivityGerobakBinding(@NonNull RelativeLayout rootView, @NonNull Button btnJualSampah,
      @NonNull RelativeLayout rlTotal, @NonNull RecyclerView rvGerobak, @NonNull TextView tvComp1,
      @NonNull TextView tvComp2, @NonNull TextView tvTitlePage, @NonNull TextView tvTotalBerat,
      @NonNull TextView tvTotalHarga) {
    this.rootView = rootView;
    this.btnJualSampah = btnJualSampah;
    this.rlTotal = rlTotal;
    this.rvGerobak = rvGerobak;
    this.tvComp1 = tvComp1;
    this.tvComp2 = tvComp2;
    this.tvTitlePage = tvTitlePage;
    this.tvTotalBerat = tvTotalBerat;
    this.tvTotalHarga = tvTotalHarga;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGerobakBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGerobakBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_gerobak, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGerobakBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_jual_sampah;
      Button btnJualSampah = ViewBindings.findChildViewById(rootView, id);
      if (btnJualSampah == null) {
        break missingId;
      }

      id = R.id.rl_total;
      RelativeLayout rlTotal = ViewBindings.findChildViewById(rootView, id);
      if (rlTotal == null) {
        break missingId;
      }

      id = R.id.rv_gerobak;
      RecyclerView rvGerobak = ViewBindings.findChildViewById(rootView, id);
      if (rvGerobak == null) {
        break missingId;
      }

      id = R.id.tv_comp1;
      TextView tvComp1 = ViewBindings.findChildViewById(rootView, id);
      if (tvComp1 == null) {
        break missingId;
      }

      id = R.id.tv_comp2;
      TextView tvComp2 = ViewBindings.findChildViewById(rootView, id);
      if (tvComp2 == null) {
        break missingId;
      }

      id = R.id.tv_title_page;
      TextView tvTitlePage = ViewBindings.findChildViewById(rootView, id);
      if (tvTitlePage == null) {
        break missingId;
      }

      id = R.id.tv_total_berat;
      TextView tvTotalBerat = ViewBindings.findChildViewById(rootView, id);
      if (tvTotalBerat == null) {
        break missingId;
      }

      id = R.id.tv_total_harga;
      TextView tvTotalHarga = ViewBindings.findChildViewById(rootView, id);
      if (tvTotalHarga == null) {
        break missingId;
      }

      return new ActivityGerobakBinding((RelativeLayout) rootView, btnJualSampah, rlTotal,
          rvGerobak, tvComp1, tvComp2, tvTitlePage, tvTotalBerat, tvTotalHarga);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
