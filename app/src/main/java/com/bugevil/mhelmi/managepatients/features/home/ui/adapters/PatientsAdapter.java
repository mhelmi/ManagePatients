package com.bugevil.mhelmi.managepatients.features.home.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bugevil.mhelmi.managepatients.R;
import com.bugevil.mhelmi.managepatients.databinding.ItemPatientBinding;
import com.bugevil.mhelmi.managepatients.features.home.data.models.Patient;
import java.util.*;

public class PatientsAdapter extends ListAdapter<Patient, PatientsAdapter.PatientsViewHolder> {

  public PatientsAdapter() {
    super(diffCallback);
  }

  private static DiffUtil.ItemCallback<Patient> diffCallback =
    new DiffUtil.ItemCallback<Patient>() {
      @Override public boolean areItemsTheSame(@NonNull Patient oldItem, @NonNull Patient newItem) {
        return oldItem.getId() == newItem.getId();
      }

      @Override
      public boolean areContentsTheSame(@NonNull Patient oldItem, @NonNull Patient newItem) {
        return oldItem.equals(newItem);
      }
    };

  @NonNull @Override
  public PatientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemPatientBinding binding =
      ItemPatientBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new PatientsViewHolder(binding);
  }

  @Override public void onBindViewHolder(@NonNull PatientsViewHolder holder, int position) {
    holder.bindItem(getItem(position));
  }

  static class PatientsViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ItemPatientBinding binding;

    public PatientsViewHolder(@NonNull ItemPatientBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      context = binding.getRoot().getContext();
    }

    private void bindItem(Patient item) {
      binding.tvPatientData.setText(item.toString());
    }
  }

  public interface OnItemClickListener {
    void onItemClick(Patient item);
  }
}