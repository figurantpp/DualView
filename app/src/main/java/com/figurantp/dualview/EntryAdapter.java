package com.figurantp.dualview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EntryAdapter extends  RecyclerView.Adapter<EntryAdapter.EntryHolder> {


    private Context context;

    private List<Pair<Entry,Entry>> entries;


    // TODO: IMPLEMENT PAYLOAD
    private int selectedIndex = RecyclerView.NO_POSITION;
    private boolean isFirst;


    public EntryAdapter(Context context, List<Entry> entries) {
        this.context = context;
        loadEntries(entries);
    }

    private void loadEntries(List<Entry> source) {

        context.getString(R.string.txtKey);
        this.entries = new ArrayList<>();

        int i;
        boolean isOdd = (source.size() % 2 != 0);

        for (i = 0; i < source.size() - 1; i+=2 ) {
            Entry first = source.get(i);
            Entry second = source.get(i+1);


            if (first == null)
                throw  new IllegalStateException
                        ("The entry of the " + i + "th position is null.");

            if (!isOdd && second == null)
                throw new IllegalStateException
                        ("The entry of the " + (i + 1) + "th position is null.");

            this.entries.add(new Pair<>(first, source.get(i + 1)));
        }

        if (isOdd) {
            this.entries.add(new Pair<Entry, Entry>(source.get(i), null));
        }

    }

    @NonNull
    @Override
    public EntryHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        return new EntryHolder(inflater.inflate(R.layout.row, container, false));
    }


    @Override
    public void onBindViewHolder(@NonNull EntryHolder row, int position) {
        Pair<Entry,Entry> selectedEntries = entries.get(position);

        if (selectedEntries.first == null) {
            throw new IllegalStateException
                    ("The first entry of the " + position + "th position is null.");
        }

        row.txtFirstKey.setText(selectedEntries.first.getKey());
        row.txtFirstValue.setText(selectedEntries.first.getValue());

        //



        if (selectedEntries.second == null) {
            row.secondFrame.setVisibility(View.INVISIBLE);
        }
        else
        {
            row.txtSecondKey.setText(selectedEntries.second.getKey());
            row.txtSecondValue.setText(selectedEntries.second.getValue());
        }


        boolean isSelected = selectedIndex == position;

        row.firstFrame.setSelected(isSelected && isFirst);
        row.secondFrame.setSelected(isSelected && !isFirst);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class EntryHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener  {

        public FrameLayout firstFrame;
        public FrameLayout secondFrame;

        public TextView txtFirstKey;
        public TextView txtFirstValue;

        public TextView txtSecondKey;
        public TextView txtSecondValue;



        public EntryHolder(@NonNull View itemView) {
            super(itemView);

            txtFirstValue  = itemView.findViewById(R.id.rowTxtFirstValueData);
            txtFirstKey    = itemView.findViewById(R.id.rowTxtFirstKeyData);
            txtSecondValue = itemView.findViewById(R.id.rowTxtSecondValueData);
            txtSecondKey   = itemView.findViewById(R.id.rowTxtSecondKeyData);

            firstFrame  = itemView.findViewById(R.id.rowFirstFrame);
            secondFrame = itemView.findViewById(R.id.rowSecondFrame);

            firstFrame.setOnClickListener(this);
            secondFrame.setOnClickListener(this);
        }

        @Override
        public void onClick(View clickedView) {

            int oldIndex = selectedIndex;
            selectedIndex = getAdapterPosition();

            int clickedId = clickedView.getId();

            isFirst = clickedId == R.id.rowFirstFrame;


            notifyItemChanged(oldIndex);
            notifyItemChanged(selectedIndex);

        }
    }
}
