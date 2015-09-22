package tonywis.tests.contacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class ActivityAjoutModifContact extends AppCompatActivity {

    private Bitmap imgCtc = null;
    private File mFichier = null;
    private ImageView img = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_modif_contact);

        Intent intent = getIntent();
        if(intent.hasExtra("dataCtc")) {
            String[] str = intent.getStringArrayExtra("dataCtc");
            ((EditText)findViewById(R.id.edTxtNom)).setText(str[0]);
            ((EditText)findViewById(R.id.edTxtPrenom)).setText(str[1]);
            ((EditText)findViewById(R.id.edTxtMail)).setText(str[2]);
            ((EditText)findViewById(R.id.edTxtTel)).setText(str[3]);
            if(intent.hasExtra("dataImg") == false)
                ((ImageView)findViewById(R.id.imageViewCtc)).setImageResource(R.mipmap.ic_contact);
            else {
                Bitmap bmp = Formatage.byteToBitmap(intent.getByteArrayExtra("dataImg"));
                ((ImageView) findViewById(R.id.imageViewCtc)).setImageBitmap(bmp);
                imgCtc = bmp;
            }
        }

        mFichier = new File(Environment.getExternalStorageDirectory(), "photoCtc.jpg");

        Button valider = (Button) findViewById(R.id.buttonSave);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                String[] str = new String[]{((EditText)findViewById(R.id.edTxtNom)).getText().toString(), ((EditText)findViewById(R.id.edTxtPrenom)).getText().toString(), ((EditText)findViewById(R.id.edTxtMail)).getText().toString(), ((EditText)findViewById(R.id.edTxtTel)).getText().toString()};
                data.putExtra("dataCtc", str);
                data.putExtra("dataImg", imgCtc == null ? null : Formatage.bitmapToByte(imgCtc));
                setResult(RESULT_OK, data);
                finish();
            }
        });

        img = (ImageView) findViewById(R.id.imageViewCtc);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri fileUri = Uri.fromFile(mFichier);
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(i, 3);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_ajout_modif_contact, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3 && resultCode == RESULT_OK) {
            imgCtc = BitmapFactory.decodeFile(mFichier.getPath());
            img.setImageBitmap(imgCtc);
        }
    }
}
