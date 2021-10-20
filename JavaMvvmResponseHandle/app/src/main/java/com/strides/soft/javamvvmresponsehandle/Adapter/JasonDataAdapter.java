package com.strides.soft.javamvvmresponsehandle.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.strides.soft.javamvvmresponsehandle.Models.ApiModel;
import com.strides.soft.javamvvmresponsehandle.Views.MainActivity;
import com.strides.soft.javamvvmresponsehandle.R;
import com.strides.soft.javamvvmresponsehandle.databinding.ItemJasonDataBinding;

import java.util.List;

public class JasonDataAdapter extends RecyclerView.Adapter<JasonDataAdapter.ViewHolder> {
    private ItemJasonDataBinding binding;
    //public ArrayList<ApiModel> jasonDataList;
    public List<ApiModel> jasonDataList;
    private LayoutInflater mInflater;
    private Context context;

    public JasonDataAdapter(MainActivity mainActivity, List<ApiModel> jasonDataList) {
        this.context = mainActivity;
        this.mInflater = LayoutInflater.from(context);
        this.jasonDataList = jasonDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_jason_data, parent, false);
        binding = DataBindingUtil.bind(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JasonDataAdapter.ViewHolder holder, int position) {
        //holder.tvVehicleName.setText(vTypeList.get(position).getText());
        binding.tvTittle.setText("Tittle: "+jasonDataList.get(position).getTitle());
        binding.tvId.setText("Id: "+jasonDataList.get(position).getId());
        binding.tvUserid.setText("UserID: "+jasonDataList.get(position).getUserId());
        binding.tvBody.setText("Body: "+jasonDataList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return jasonDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        /*TextView tittleTv;
        TextView idTv;
        TextView useridTv;
        TextView bodyTv;*/
        public ViewHolder(View itemView) {
            super(itemView);

           /* tittleTv = (TextView) itemView.findViewById(R.id.tv_tittle);
            idTv = (TextView) itemView.findViewById(R.id.tv_id);
            useridTv = (TextView) itemView.findViewById(R.id.tv_userid);
            bodyTv = (TextView) itemView.findViewById(R.id.tv_body);*/

        }
    }

}
