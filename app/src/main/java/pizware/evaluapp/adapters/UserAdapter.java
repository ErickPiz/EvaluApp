package pizware.evaluapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pizware.evaluapp.Models.User;
import pizware.evaluapp.R;

/**
 * Created by Piz on 02/02/2018.
 */

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;
    private int layout;

    public UserAdapter(Context context, List<User> users, int layout) {
        this.context = context;
        this.list = users;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public User getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.lblUser = (TextView) convertView.findViewById(R.id.lblUser);
            vh.lblPassword = (TextView) convertView.findViewById(R.id.lblPassword);
            vh.lblEmail = (TextView) convertView.findViewById(R.id.lblEmail);
            vh.lblAdmin = (TextView) convertView.findViewById(R.id.chbAdmin);
            vh.lblHint = (TextView) convertView.findViewById(R.id.lblHint);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        User user = list.get(position);
        vh.lblUser.setText(user.getUser());
        vh.lblPassword.setText(user.getPassword());
        vh.lblEmail.setText(user.getEmail());
        if(user.isAdmin())
            vh.lblAdmin.setText("Admin");
        else
            vh.lblAdmin.setText("Not admin");
        vh.lblHint.setText(user.getHint());

        return convertView;
    }
    public class ViewHolder {
        TextView lblUser;
        TextView lblPassword;
        TextView lblEmail;
        TextView lblAdmin;
        TextView lblHint;
    }
}



