package exam.jmedinilla.offering_jmedinilla;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * @author Javier Medinilla
 * @version 1.0
 *          <p>
 *          Activity with the list that has to be shown to the
 *          user of the application. It's filled thanks to the
 *          adapter. The Activity's menu lets the user choose
 *          the order for the items by name or type (default)
 */
public class ListActivity extends AppCompatActivity {

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        boolean importance = false;
        Repository repository = Repository.getInstance();
        boolean[] selectedItems = getIntent().getExtras().getBooleanArray("selected_items");
        ArrayList<Offer> listAdapter = new ArrayList<>();
        if (selectedItems != null) {
            if (selectedItems[0]) {
                listAdapter.addAll(repository.getHomeOffers());
            }
            if (selectedItems[1]) {
                listAdapter.addAll(repository.getElectronicsOffers());
            }
            if (selectedItems[2]) {
                listAdapter.addAll(repository.getSportOffers());
            }
            if (selectedItems[3]) {
                importance = true;
            }
        }
        adapter = new ListAdapter(ListActivity.this, listAdapter, importance);

        ListView listOffer = (ListView) findViewById(R.id.list_lstOffer);
        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.list_btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, ManageActivity.class);
                startActivity(intent);
            }
        });

        listOffer.setAdapter(adapter);
        registerForContextMenu(listOffer);
        adapter.notifyChanges();
    }

    private void showAuthorDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(ListActivity.this);
        dialog.setTitle(R.string.list_activity_dialog_title);
        dialog.setMessage(R.string.list_activity_dialog_message);
        dialog.setPositiveButton(R.string.list_activity_dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        dialog.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list_ordernameasc:
                adapter.orderOffer(ListAdapter.ORDER_NAME_ASC);
                break;
            case R.id.action_list_ordernamedesc:
                adapter.orderOffer(ListAdapter.ORDER_NAME_DESC);
                break;
            case R.id.action_list_ordertype:
                adapter.orderOffer(ListAdapter.ORDER_TYPE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show:
                showAuthorDialog();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
