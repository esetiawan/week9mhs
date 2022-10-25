package id.ac.week9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MahasiswaAdapter(val context: Context,
                       val listMhs:List<Mahasiswa>):
    RecyclerView.Adapter<MahasiswaAdapter.ListViewHolder>() {
    private var onItemClickListener:OnRecyclerViewItemClickListener?=null
    inner class ListViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val mhsImageView: ImageView = view.findViewById(R.id.mhs_image_iv)
        val mhsNamaTextView: TextView = view.findViewById(R.id.mhs_nama_tv)
        val mhsDetailTextView: TextView = view.findViewById(R.id.mhs_detail_tv)
        init {
            view.setOnClickListener {
                onItemClickListener?.OnClick(it,adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        //menentukan xml layout untuk sebuah item dari RecyclerView
        val itemView = LayoutInflater.from(context).inflate(R.layout.mhs_list_item,parent,false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //mengisi widget dengan data
        val mhs = listMhs[position]
        holder.mhsImageView.setImageResource(
            when(mhs.jurusan)
            {
                "D3"->{ android.R.drawable.star_on  }
                "S1"->{ android.R.drawable.star_big_off }
                "S2"->{ android.R.drawable.star_big_on }
                else->{ android.R.drawable.ic_delete }
            }
        )
        holder.mhsNamaTextView.text=mhs.nama
        holder.mhsDetailTextView.text="${mhs.nrp} - ${mhs.umur} - ${mhs.jurusan}"
    }

    override fun getItemCount(): Int {
        return listMhs.size
    }
    fun setOnItemClickListener(onItemClickListener: OnRecyclerViewItemClickListener){
        this.onItemClickListener = onItemClickListener
    }
}

interface OnRecyclerViewItemClickListener {
    fun OnClick(view:View,position:Int)
}
