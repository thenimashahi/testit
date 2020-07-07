package com.example.midterm2_skeletonproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private DBHandler mDBHandler;

    static ModelYearDialogFragment newInstance(String owner, String img) {
        ModelYearDialogFragment f = new ModelYearDialogFragment();
        Bundle args = new Bundle();
        args.putString("owner", owner);
        args.putString("image", img);
        f.setArguments(args);

        return f;
    }

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

        mDBHandler = new DBHandler(view.getContext(), "null", null, 1);

        final NumberPicker yearPicker = (NumberPicker) view.findViewById(R.id.yearPicker);
        final Spinner modelSpinner = view.findViewById(R.id.modelSpinner);
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
                int year = yearPicker.getValue();
                String model = modelSpinner.getSelectedItem().toString();
                saveCar(year, model);
            }
        });
    }

    private void saveCar(int year, String model) {
        assert getArguments() != null;
        String owner = getArguments().getString("owner");
        String img = getArguments().getString("image");
        mDBHandler.addCar(new Car(owner, img, year, model));

        this.dismiss();
    }
}
