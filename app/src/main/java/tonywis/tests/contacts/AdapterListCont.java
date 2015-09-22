package tonywis.tests.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;

/**
 * Created by Tony on 22/09/2015.
 */
public class AdapterListCont extends ArrayAdapter<Contact> {

    private LayoutInflater inflater;
    private Context context;

    public AdapterListCont(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void add(Contact object) {
        super.add(object);
    }

    @Override
    public void addAll(Collection<? extends Contact> collection) {
        super.addAll(collection);
    }

    @Override
    public void remove(Contact object) {
        super.remove(object);
    }

    @Override
    public Contact getItem(int position) {
        return super.getItem(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder
    {
        public ImageButton hImgContact;
        public TextView hNomContact;
        public ImageButton hButtonAppel;
        public RelativeLayout hItemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //Log.i("Log getView", Integer.toString(position));
        final Contact ctc = getItem(position);

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.layout_item_contact, null);
            holder = new ViewHolder();
            holder.hImgContact = (ImageButton) convertView.findViewById(R.id.imgContact);
            holder.hNomContact = (TextView) convertView.findViewById(R.id.nomContact);
            holder.hButtonAppel = (ImageButton) convertView.findViewById(R.id.buttonAppel);
            holder.hItemList = (RelativeLayout) convertView.findViewById(R.id.itemList);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        if(ctc != null) {
            holder.hNomContact.setText(ctc.getPrenom() + " " + ctc.getNom());
            holder.hButtonAppel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ctc.getPhone()));
                    context.startActivity(appel);

                }
            });
            if(ctc.getImg() == null) {
                holder.hImgContact.setImageResource(R.mipmap.ic_contact);
            }
            else {
                /*try {
                    InputStream is = context.getContentResolver().openInputStream(ctc.getImg());
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize = 10;
                    Bitmap preview_bitmap=BitmapFactory.decodeStream(is,null,options);

                    Drawable icon = new BitmapDrawable(context.getResources(),preview_bitmap);
                    holder.hImgContact.setBackground(icon);

                } catch (FileNotFoundException e) {
                    holder.hImgContact.setImageResource(R.mipmap.ic_contact);
                }*/
                if(ctc.getImg() == null)
                    holder.hImgContact.setImageResource(R.mipmap.ic_contact);
                else
                    holder.hImgContact.setImageBitmap(ctc.getImg());
            }

            holder.hItemList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ActivityAjoutModifContact.class);
                    String[] str = new String[]{ctc.getNom(), ctc.getPrenom(), ctc.getMail(), ctc.getPhone()};
                    intent.putExtra("dataCtc", str);
                    if(ctc.getImg() != null)
                        intent.putExtra("dataImg", Formatage.bitmapToByte(ctc.getImg()));
                    // 2 pour Maj contact
                    ((Activity) v.getContext()).startActivityForResult(intent, 2);
                }
            });


            /**/
        }

        return convertView;
    }
}
