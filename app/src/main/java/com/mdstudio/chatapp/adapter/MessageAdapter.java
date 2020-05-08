package com.mdstudio.chatapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mdstudio.chatapp.R;
import com.mdstudio.chatapp.model.FriendlyMessage;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewModel> {

    private List<FriendlyMessage> list = new ArrayList<>();

    public void setList(List<FriendlyMessage> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewModel(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.li_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewModel holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MessageViewModel extends RecyclerView.ViewHolder {

        // Declare your views
        private TextView message;
        private TextView name;

        public MessageViewModel(@NonNull View itemView) {
            super(itemView);

            // inflate the view
            name = itemView.findViewById(R.id.li_msg_name);
            message = itemView.findViewById(R.id.li_msg_message);

        }

        private void bind(int position) {
            // Bind data
            name.setText(list.get(position).getName());
            message.setText(list.get(position).getText());

        }
    }
}
