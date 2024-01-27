package com.example.mall;

import static android.webkit.URLUtil.isValidUrl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;


public class BarcodeFragment extends Fragment {

    private TextView email;
    private CodeScanner codeScanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);

        email = view.findViewById(R.id.email);
        CodeScannerView codeScannerView = view.findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(requireContext(), codeScannerView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                String scannedText = result.getText();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        email.setText(scannedText);
                        email.setText(result.getText().toString());
                        if (isUrl(scannedText)) {
                            Intent intent = new Intent(requireContext(), WebViewActivity.class);
                            intent.putExtra("url", scannedText);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });

        permissionCheck();

        return view;
    }

    private void permissionCheck() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 12);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 12) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission granted, start the preview
                codeScanner.startPreview();
            } else {
                // Handle the case when permission is denied
                Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isUrl(String text) {
        return text.startsWith("http://") || text.startsWith("https://");
    }

    @Override
    public void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    public void onPause() {
        super.onPause();
        codeScanner.stopPreview();
    }
}
