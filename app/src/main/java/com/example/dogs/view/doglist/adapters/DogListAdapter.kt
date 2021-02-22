package com.example.dogs.view.dogList.adapters

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.dogs.R
import com.example.dogs.util.getProgressDrawable
import com.example.dogs.util.loadImage
import com.example.dogs.model.DogBreed
import com.example.dogs.util.setBackgroundDrawableColor
import com.example.dogs.view.dogList.ListFragmentDirections
import kotlinx.android.synthetic.main.item_dog.view.*
import java.util.*
import kotlin.collections.ArrayList

class DogListAdapter(val dogsList:ArrayList<DogBreed>):RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    fun updateDogList(newDogList:List<DogBreed>){
        dogsList.clear();
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }

    class DogViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_dog,parent,false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {



        holder.view.name.text=dogsList[position].dogbreed
        holder.view.lifespan.text=dogsList[position].lifespan
        holder.view.setOnClickListener {
            val uuid = dogsList[position].uuid.toString().toInt()
            Log.d("Adaper",uuid.toString())
            val action = ListFragmentDirections.toDetailFragment()
            action.dogUuid = uuid
            Navigation.findNavController(it).navigate(action)
        }

        Glide.with(holder.view.imageView.context)
            .asBitmap()
            .load(dogsList[position].imageUrl)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    holder.view.imageView.setImageBitmap(resource)
                        Palette.from(resource).generate(object :Palette.PaletteAsyncListener{
                            override fun onGenerated(palette: Palette?) {
                                if (palette != null) {
                                    holder.view.setBackgroundDrawableColor(palette.getMutedColor(Color.WHITE))
                                }
                            }

                        })
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }

            })


     /*   holder.view.imageView.loadImage(dogsList[position].imageUrl,getProgressDrawable(holder.view.imageView.context)).also {
            val palete= Palette.generate(holder.view.imageView.drawable.toBitmap())
            holder.view.apply {

                setBackgroundColor(
                    palete.getDominantColor(Color.WHITE)
                )
            }
        }*/
    }

    override fun getItemCount()=dogsList.size

}

