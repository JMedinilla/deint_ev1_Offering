package exam.jmedinilla.offering_jmedinilla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Medinilla
 * @version 1.0
 *          <p>
 *          Offer manage Activity. It lets the user to add
 *          a new offer to the Repository list so he can see
 *          it on the ListActivity
 */
public class ManageActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtShop;
    EditText edtDate;
    Spinner spinType;
    Spinner spinImportance;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        final Repository repository = Repository.getInstance();

        List<String> spinnerTypeArray = new ArrayList<>();
        spinnerTypeArray.add("Hogar");
        spinnerTypeArray.add("Electrónica");
        spinnerTypeArray.add("Deporte");

        final List<String> spinnerImportanceArray = new ArrayList<>();
        spinnerImportanceArray.add("Baja");
        spinnerImportanceArray.add("Media");
        spinnerImportanceArray.add("Alta");

        edtName = (EditText) findViewById(R.id.manage_edtName);
        edtShop = (EditText) findViewById(R.id.manage_edtShop);
        edtDate = (EditText) findViewById(R.id.manage_edtDate);
        spinType = (Spinner) findViewById(R.id.manage_spinType);
        spinImportance = (Spinner) findViewById(R.id.manage_spinImportance);
        btnAdd = (Button) findViewById(R.id.manage_btnOk);

        ArrayAdapter<String> adapterType = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerTypeArray);
        ArrayAdapter<String> adapterImportance = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerImportanceArray);

        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterImportance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinType.setAdapter(adapterType);
        spinImportance.setAdapter(adapterImportance);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmpName = edtName.getText().toString();
                String tmpShop = edtShop.getText().toString();
                String tmpDate = edtDate.getText().toString();
                int tmpType = spinType.getSelectedItemPosition();
                int tmpImportance = spinImportance.getSelectedItemPosition();

                if (!tmpName.equals("") && !tmpShop.equals("") && !tmpDate.equals("")) {
                    Offer offer = new Offer(tmpName, tmpShop, tmpDate, tmpType, tmpImportance);
                    if (repository.contains(offer)) {
                        Toast.makeText(ManageActivity.this, R.string.manage_exists, Toast.LENGTH_SHORT).show();
                    } else {
                        repository.addOffer(offer);
                        Toast.makeText(ManageActivity.this, R.string.manage_added, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(ManageActivity.this, R.string.manage_empty, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
