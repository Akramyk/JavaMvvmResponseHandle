package com.strides.soft.mvvmretrofitfragmentkotlin.Views.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.strides.soft.mvvmretrofitfragmentkotlin.Models.PatientHistoryModel
import com.strides.soft.mvvmretrofitfragmentkotlin.R
import com.strides.soft.mvvmretrofitfragmentkotlin.ViewModels.PatientViewModel
import com.strides.soft.mvvmretrofitfragmentkotlin.Views.Adapters.PatientListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.reflect.Array.newInstance


class PatientListFragment : Fragment(){

    private val patientListModel: PatientViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_patient_list, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet", "WrongConstant")
    override fun onStart() {
        super.onStart()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rv_patient_list)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)

        patientListModel.getPatients()
        patientListModel.listOfPatients.observe(this, Observer(function = fun(patientList : List<PatientHistoryModel>?){
            patientList?.let {
                var patientListAdapter : PatientListAdapter = PatientListAdapter(patientList)
                recyclerView.adapter = patientListAdapter
                patientListAdapter.setItemClickListener(object : PatientListAdapter.ItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                        val newFragment = PatientDetailFragment.newInstance(patientList.get(position))
                        val transaction = fragmentManager!!.beginTransaction()
                        transaction.replace(R.id.frag_container, newFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                })

            }

        }))
    }
}