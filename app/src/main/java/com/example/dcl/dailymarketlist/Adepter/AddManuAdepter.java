package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.AddMarketList.AddMarketList;
import com.example.dcl.dailymarketlist.AddMarketList.ItemButtonClick;
import com.example.dcl.dailymarketlist.Model.AddMenu;
import com.example.dcl.dailymarketlist.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.example.dcl.dailymarketlist.AddMarketList.AddMarketList.change_item;
import static com.example.dcl.dailymarketlist.AddMarketList.AddMarketList.change_quantity;
import static com.example.dcl.dailymarketlist.AddMarketList.AddMarketList.change_quantity_types;

public class AddManuAdepter extends RecyclerView.Adapter<AddManuAdepter.AddManuViewHolder>
{
    private Context context;
    private List<AddMenu> add_menu_list = new ArrayList<>();
    private ItemButtonClick itemButtonClick;
    boolean isClicked;
    public  int value=0;
    public String quntity_mark;

    String quntity_markes_1;
    public static String quntity_markes_2;

    public AddManuAdepter(Context context, List<AddMenu> add_menu_list, int value)
    {
        this.add_menu_list = add_menu_list;
        this.context = context;
        this.value = value;
    }

    public AddManuAdepter(Context context, boolean isClicked )   {
        this.context = context;
        this.isClicked = isClicked;
        this.add_menu_list=add_menu_list;
    }
    public void setItemButtonClick(ItemButtonClick itemButtonClick) {
        this.itemButtonClick = itemButtonClick;
    }

    public AddManuAdepter(List<AddMenu> add_menu_list,Context context) {
        this.context = context;
        this.add_menu_list=add_menu_list;
        if (add_menu_list.size()==0)
        {
            String a="";
            String b="";


            add_menu_list.add(new AddMenu(a,b));
        }else {
            return;
        }
    }

    @NonNull
    @Override
    public AddManuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_item_design,null);
        return new AddManuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddManuViewHolder addManuViewHolder, final int i)
    {
        addManuViewHolder.add_manu.setText(add_menu_list.get(i).getMenu_name());
        addManuViewHolder.add_quantity.setText(add_menu_list.get(i).getQuantity());

        if (add_menu_list.get(i).getQuntity_marks() ==null){
            addManuViewHolder.spinner.setText("g");

        }else {
            addManuViewHolder.spinner.setText(add_menu_list.get(i).getQuntity_marks());
        }





      /*  addManuViewHolder.spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if(i == add_menu_list.size()-1)
                {
                    quntity_markes_1 = item.toString();
                }else {
                   quntity_markes_2=item.toString();
                }
            }
        });*/

        addManuViewHolder.add_new_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                quntity_markes_1=addManuViewHolder.spinner.getText().toString();


                change_item = "";
                change_quantity = "";

                if (addManuViewHolder.add_manu.getText().toString().isEmpty() || addManuViewHolder.add_quantity.getText().toString().isEmpty()){
                    Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show();
                }else {

                    add_menu_list.set(add_menu_list.size()-1,new AddMenu(addManuViewHolder.add_manu.getText().toString(),addManuViewHolder.add_quantity.getText().toString(),quntity_markes_1));

                    add_menu_list.add(new AddMenu("","","g"));
                    notifyDataSetChanged();
                }
            }
        });


        addManuViewHolder.add_manu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(i == add_menu_list.size()-1)
                {
                    change_item = s.toString();
                }

            }
            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        addManuViewHolder.add_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                if(i == add_menu_list.size()-1) {
                    change_quantity = s.toString();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       /* if (addManuViewHolder.add_new_item.isShown()  && i== add_menu_list.size()-1)
        {
            quntity_markes_1= addManuViewHolder.spinner.getText().toString();

            Toast.makeText(context,"BALLLL "+quntity_markes_1,Toast.LENGTH_SHORT).show();

        }*/
       addManuViewHolder.spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
           @Override
           public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
               quntity_markes_2=item.toString();
           }
       });


        if (i==add_menu_list.size()-1)
        {
            addManuViewHolder.add_new_item.setVisibility(View.VISIBLE);
            addManuViewHolder.add_manu.setCursorVisible(true);
            addManuViewHolder.add_quantity.setCursorVisible(true);
        }
        else
         {
            addManuViewHolder.add_new_item.setVisibility(View.GONE);
            addManuViewHolder.add_manu.setCursorVisible(false);
            addManuViewHolder.add_quantity.setCursorVisible(false);
        }

    }

    @Override
    public int getItemCount()
    {
        return add_menu_list.size();
    }

    public class AddManuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       public  EditText add_manu, add_quantity;
            ImageView add_new_item;
            MaterialSpinner spinner;


        public AddManuViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner= (MaterialSpinner)itemView.findViewById(R.id.spinner);
            add_manu=itemView.findViewById(R.id.bazar_product_name);
            add_quantity=itemView.findViewById(R.id.quantity);
            add_new_item=itemView.findViewById(R.id.add_new);
            spinner.setItems("g", "Kg", "Pic", "Liter");
        }

        @Override
        public void onClick(View v)
        {
            itemButtonClick.onItemButtonClick(v,getAdapterPosition());
        }
    }
}
