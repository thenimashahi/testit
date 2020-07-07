package com.example.midterm2_skeletonproject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Objects;

public class ModelYearDialogFragment extends DialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_model_year_dialog, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NumberPicker yearPicker = (NumberPicker) view.findViewById(R.id.yearPicker);
        Spinner modelSpinner = view.findViewById(R.id.modelSpinner);
        Button saveCarBtn = view.findViewById(R.id.saveCarBtn);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        yearPicker.setMinValue(1990);
        yearPicker.setMaxValue(3500);
        yearPicker.setValue(year);

        String[] models = new String[] { "Nissan", "Ford", "Texas"};
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, models);
        modelSpinner.setAdapter(modelAdapter);

        saveCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCar();
            }
        });
    }

    private void saveCar() {
        this.dismiss();
    }
}
