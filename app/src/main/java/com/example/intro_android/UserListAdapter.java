package com.example.intro_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    List<User> mylist;
    User User = new User();
    ImageView img;

    public UserListAdapter(Context _context, List<User> _mylist) {
        super(_context, R.layout.account_list_row, _mylist);

        this.mylist = _mylist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        convertView = vi.inflate(R.layout.account_list_row, parent, false);

        // User object
        User = getItem(position);

        TextView accountEmail = convertView.findViewById(R.id.accountEmail);
        accountEmail.setText(User.getEmail());

        TextView accountName = convertView.findViewById(R.id.accountName);
        accountName.setText(User.getName());

        // show image
        img = convertView.findViewById(R.id.accountImage);

        Picasso.get().load(User.getUrl()).into(img);

        return convertView;
    }
}
