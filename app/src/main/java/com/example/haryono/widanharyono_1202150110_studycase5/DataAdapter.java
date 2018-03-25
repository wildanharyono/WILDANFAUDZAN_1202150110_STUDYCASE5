package com.example.haryono.widanharyono_1202150110_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by haryono on 3/25/2018.
 */

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.holder> {
        private Context mContext;
        private List<Data> mList;
        int mColor;

        public DataAdapter(Context mContext, List<Data> mList, int mColor) {
            this.mContext = mContext;
            this.mList = mList;
            this.mColor = mColor;
        }

        @Override
        public holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            holder hldr = new holder(view);
            return hldr;
        }


        @Override
        public void onBindViewHolder(holder holder, int position) {
            Data data = mList.get(position);
            holder.todoName.setText(data.getTodoName());
            holder.todoDescription.setText(data.getTodoDesc());
            holder.todoPriority.setText(data.getTodoPriority());
            holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(this.mColor));
        }

        //mendapatkan jumlah mList
        @Override
        public int getItemCount() {
            return mList.size();
        }


        public Data getData(int position) {//mendapatkan mList dari mAdapter
            return mList.get(position);
        }


        public void deleteData(int i) {
            mList.remove(i);//remove item
            notifyItemRemoved(i);
            notifyItemRangeChanged(i, mList.size());
        }

        class holder extends RecyclerView.ViewHolder {
            //deklarasi variable yang akan digunakan
            public TextView todoName, todoDescription, todoPriority;
            public CardView cardView;

            public holder(View itemView) {
                super(itemView);

                //mengakses id text view pada layout dan juga cardview
                todoName = itemView.findViewById(R.id.title);
                todoDescription = itemView.findViewById(R.id.subTitle);
                todoPriority = itemView.findViewById(R.id.priority);
                cardView = itemView.findViewById(R.id.cardlist);
            }
        }

    }

