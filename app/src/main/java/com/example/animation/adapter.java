package com.example.animation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import static com.example.animation.Details.nofp;
import static com.example.animation.animation.count;
import static com.example.animation.animation.count1;
import java.util.List;
import es.dmoral.toasty.Toasty;
import static com.example.animation.animation.pay;

public class adapter extends RecyclerView.Adapter<adapter.viewholder>{



    public static  int total=0;
    List<Model>a;
    Context context;
    private int lastpos=-1;
    public adapter(List<Model>a,Context context)
    {
        this.a=a;
        this.context=context;
    }
    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.viewlayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, final int position) {

        final Model model=a.get(position);
        holder.text.setText(model.getDes());
        holder.img.setImageDrawable(context.getResources().getDrawable(model.getImage()));

        fade(holder.itemView);
        if(model.test) {
            holder.itemView.setScaleX(0.8f);
            holder.itemView.setScaleY(0.8f);
            holder.itemView.setBackgroundColor(Color.parseColor("#5F5F5F"));
        }
        else
        {
            holder.itemView.setScaleX(1.0f);
            holder.itemView.setScaleY(1.0f);
            holder.itemView.setBackgroundColor(Color.parseColor("#272523"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(!model.test) {
                        if(nofp<=model.numeve) {
                            holder.itemView.setScaleX(0.8f);
                            holder.itemView.setScaleY(0.8f);
                            holder.itemView.setBackgroundColor(Color.parseColor("#5F5F5F"));
                            model.test = true;
                            total += pay[position];
                            count++;
                        }
                        else
                        {
                            Toasty.info(context, "This event requires less number of participants", Toasty.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        holder.itemView.setScaleX(1.0f);
                        holder.itemView.setScaleY(1.0f);
                        holder.itemView.setBackgroundColor(Color.parseColor("#272523"));

                        model.test=false;
                        total-= pay[position];
                        count--;
                    }
                count1=count;
            }
        });
    }
     public void fade(View v)
     {
         ScaleAnimation animation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,1.0f);
         animation.setDuration(500);
         v.startAnimation(animation);
     }
    @Override
    public int getItemCount() {
        return a.size();
    }

    public class viewholder extends ViewHolder{
        ImageView img;
        TextView text;
        public viewholder( View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            text=itemView.findViewById(R.id.textview);
        }
    }
}
