package tonywis.tests.contacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityListContacts extends AppCompatActivity {
    private ListView listVue;
    private AdapterListCont adapter;

    private List<Contact> listCtc = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        listVue = (ListView) findViewById(R.id.listViewContacts);

        adapter = new AdapterListCont(this, R.layout.layout_item_contact);
        listVue.setAdapter(adapter);
        //Contact ctc = new Contact("Wisniewski", "Tony", "toto@mail.com", "0672480280", null);
        //listCtc.add(new Contact("Wisniewski", "Tony", "toto@mail.com", "0672480280", null));
        //adapter.addAll(listCtc);

        ImageButton butAdd = (ImageButton) findViewById(R.id.buttonAdd);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListContacts.this, ActivityAjoutModifContact.class);
                // 1 pour nouveau contact
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_activity_list_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // nouveau contact
        if(data != null && data.getExtras() != null) {
            String[] listData = data.getStringArrayExtra("dataCtc");
            Bitmap bmp = data.getByteArrayExtra("dataImg") == null ? null : Formatage.byteToBitmap(data.getByteArrayExtra("dataImg"));
            Contact ctc = new Contact(listData[0], listData[1], listData[2], listData[3], bmp);
            if (requestCode == 1) {
                Toast.makeText(this, "Contact enregistré", Toast.LENGTH_SHORT).show();
                listCtc.add(ctc);
                adapter.add(ctc);
            } // MàJ
            else if(requestCode == 2) {
                for (Contact c : listCtc) {
                    if (c.getNom().compareTo(ctc.getNom()) == 0 && c.getPrenom().compareTo(ctc.getPrenom()) == 0) {
                        c.setPhone(ctc.getPhone());
                        c.setMail(ctc.getMail());
                        c.setImg(ctc.getImg());
                    }
                }
                Toast.makeText(this, "Contact mis à jour", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }
    }
}
