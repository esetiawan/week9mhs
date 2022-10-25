package id.ac.week9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    lateinit var nrpEt: EditText
    lateinit var namaEt: EditText
    lateinit var umurEt: EditText
    lateinit var jurusanSpinner: Spinner
    lateinit var insertBtn: Button
    lateinit var updateBtn: Button
    lateinit var deleteBtn: Button
    lateinit var mhsRecyclerView: RecyclerView
    val arrMahasiswa: ArrayList<Mahasiswa> = ArrayList()
    lateinit var mahasiswaAdapter:MahasiswaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nrpEt = findViewById(R.id.nrp_et)
        namaEt = findViewById(R.id.nama_et)
        umurEt = findViewById(R.id.umur_et)
        jurusanSpinner = findViewById(R.id.jurusan_spinner)
        insertBtn = findViewById(R.id.insert_btn)
        updateBtn = findViewById(R.id.update_btn)
        deleteBtn = findViewById(R.id.delete_btn)
        mhsRecyclerView = findViewById(R.id.mhs_list_rv)
        val layoutManager:RecyclerView.LayoutManager =
            //LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            GridLayoutManager(this,3)
            //StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        mahasiswaAdapter = MahasiswaAdapter(this,arrMahasiswa)
        mhsRecyclerView.layoutManager = layoutManager
        mhsRecyclerView.adapter=mahasiswaAdapter
        mahasiswaAdapter.setOnItemClickListener(object:OnRecyclerViewItemClickListener{
            override fun OnClick(view: View, position: Int) {
                Toast.makeText(view.context,"${arrMahasiswa[position].nrp}",
                Toast.LENGTH_SHORT).show()
            }

        })
        insertBtn.setOnClickListener {
            val m:Mahasiswa=Mahasiswa(nrpEt.text.toString(), namaEt.text.toString()
            ,umurEt.text.toString().toInt(),jurusanSpinner.selectedItem.toString())
            arrMahasiswa.add(m)
            //mahasiswaAdapter.notifyDataSetChanged()
            mahasiswaAdapter.notifyItemInserted(arrMahasiswa.lastIndex)
        }
    }
}