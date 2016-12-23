package exam.jmedinilla.offering_jmedinilla;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Javier Medinilla
 * @version 1.0
 *          <p>
 *          Main Activity of the class. The user must choose
 *          at least one offer type to continue and see the
 *          offers list
 */
public class MainActivity extends AppCompatActivity {

    TextView txtTitle;
    CheckBox chbHome;
    CheckBox chbElectronics;
    CheckBox chbSport;
    CheckBox chbImportance;
    Button btnOk;
    private boolean[] selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.main_txtTitle);
        chbHome = (CheckBox) findViewById(R.id.main_chbHome);
        chbElectronics = (CheckBox) findViewById(R.id.main_chbElectronics);
        chbSport = (CheckBox) findViewById(R.id.main_chbSports);
        chbImportance = (CheckBox) findViewById(R.id.main_chbImportance);
        btnOk = (Button) findViewById(R.id.main_btnOk);

        Typeface font = Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
        txtTitle.setTypeface(font);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bH = chbHome.isChecked();
                boolean bE = chbElectronics.isChecked();
                boolean bS = chbSport.isChecked();
                if (bH || bE || bS) {
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);

                    selectedItems = new boolean[]{bH, bE, bS, chbImportance.isChecked()};
                    intent.putExtra("selected_items", selectedItems);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.login_choice, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
