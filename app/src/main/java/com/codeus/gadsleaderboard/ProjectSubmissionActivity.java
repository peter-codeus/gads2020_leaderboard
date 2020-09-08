package com.codeus.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmissionActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, emailEditText, projectLinkEditText;
    Dialog submissionDialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        firstNameEditText = findViewById(R.id.firstname_edit_text);
        lastNameEditText = findViewById(R.id.lastname_edit_text);
        emailEditText = findViewById(R.id.email_address_edit_text);
        projectLinkEditText = findViewById(R.id.github_link_edit_text);
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
    }

    public void navigateBack(View view)
    {
        finish();
    }

    public void clearForm()
    {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        projectLinkEditText.setText("");
    }

    public void displayConfirmationDialog(View view)
    {
        submissionDialog = new Dialog(this);
        View dialogView = getLayoutInflater().inflate(R.layout.confirmation_dialog_layout, null);
        Button yesBtn = dialogView.findViewById(R.id.confirm_dialog_yes_btn);
        ImageView closeBtn = dialogView.findViewById(R.id.close_confirm_dialog_btn);
        closeBtn.setOnClickListener(v -> { submissionDialog.dismiss(); });
        yesBtn.setOnClickListener(v -> {
            submissionDialog.dismiss();
            submitProjectRequest();
        });

        submissionDialog.setContentView(dialogView);
        submissionDialog.setCanceledOnTouchOutside(true);
        submissionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submissionDialog.show();
    }

    public void displaySubmissionStatusDialog(boolean isSubmissionSuccessful)
    {
        Dialog submissionDialog = new Dialog(this);
        View dialogView = getLayoutInflater().inflate(R.layout.successful_submission_dialog_layout, null);
        submissionDialog.setContentView(dialogView);
        if(!isSubmissionSuccessful)
        {
            ImageView dialogImage = dialogView.findViewById(R.id.dialogIcon);
            TextView textView = dialogView.findViewById(R.id.dialogText);
            textView.setText("Submission not Successful");
            dialogImage.setImageResource(R.drawable.ic_warning_red);
        }
        submissionDialog.setCanceledOnTouchOutside(true);
        submissionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submissionDialog.show();
    }

    public void submitProjectRequest()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GadsApi.FORMS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GadsApi gadsApi = retrofit.create(GadsApi.class);

        String email = this.emailEditText.getText().toString();
        String firstName = this.firstNameEditText.getText().toString();
        String lastName = this.lastNameEditText.getText().toString();
        String githubLink = this.projectLinkEditText.getText().toString();

        Call<Void> dataCall = gadsApi.submitProject(email, firstName, lastName, githubLink);

        progressDialog.setMessage("Submitting Project ...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        dataCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                if(response.isSuccessful())
                {
                    clearForm();
                    displaySubmissionStatusDialog(true);
                }
                else
                    displaySubmissionStatusDialog(false);
                Log.i("SUBMISSION", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                displaySubmissionStatusDialog(false);
                Log.i("SUBMISSION", new Gson().toJson(t));
            }
        });
    }
}
