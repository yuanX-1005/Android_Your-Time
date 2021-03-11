package com.example.projet.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projet.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import java.util.*

class DashboardFragment : Fragment() {
  var mylineChart: LineChart? = null
  var mybarchart: BarChart? = null
  var textView: TextView ?=null
  var button_bd: Button? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
    // textView = (TextView) root.findViewById(R.id.text_dashboard);
    button_bd = root.findViewById(R.id.button_bd)
    textView = root.findViewById(R.id.textView)
    mylineChart = root.findViewById(R.id.lc_1)

    //改变获取数据的方式
    val entry: MutableList<Entry> = ArrayList()
    val c1e1 = Entry(10f, 0f) //get les element de la table "..." ;
    val c1e2 = Entry(20f, 5f)
    val c1e3 = Entry(30f, 10f)
    val c1e4 = Entry(40f, 15f)
    entry.add(c1e1)
    entry.add(c1e2)
    entry.add(c1e3)
    entry.add(c1e4)
    val data = LineDataSet(entry, "abc")
    val lineData = LineData(data)
    if(mylineChart==null) {
      mylineChart!!.setData(lineData)
    }else{
      mylineChart!!.setData(lineData)
    }

    mybarchart = root.findViewById(R.id.bc_1)
    val list: MutableList<BarEntry> = ArrayList()
    val da1 = BarEntry(0f, 5f)
    val da2 = BarEntry(5f, 6f)
    val da3 = BarEntry(10f, 10f)
    val da4 = BarEntry(15f, 12f)
    val da5 = BarEntry(20f, 15f)
    list.add(da1)
    list.add(da2)
    list.add(da3)
    list.add(da4)
    list.add(da5)
    val databar = BarData()
    val set = BarDataSet(list, "barchar")
    databar.addDataSet(set)
    databar.addDataSet(set)

    if(mybarchart==null) {
      mybarchart!!.setData(databar)
    }else{
      mybarchart!!.setData(databar)
    }



    return root
  }


 /* fun setLineChartData(){
    val xvalue = ArrayList<String>()
    xvalue.add("11.00 AM")
    xvalue.add("12.00 AM")
    xvalue.add("1.00 AM")
    xvalue.add("3.00 PM")
    xvalue.add("7.00 PM")

    val lineentry = ArrayList<Entry>();
    lineentry.add(Entry(20F, 0F))
    lineentry.add(Entry(50f, 1F))
    lineentry.add(Entry(60f, 2F))
    lineentry.add(Entry(30f, 3F))
    lineentry.add(Entry(10f, 4F))

    val linedataset = LineDataSet(lineentry, "First")
    linedataset.color = ContextCompat.getColor(requireContext(),R.color.green)

    val data = LineData(xvalue,linedataset)
    lineChart.data = data
  lineChart.setBa



  }

  */


  /*private lateinit var dashboardViewModel: DashboardViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
    val textView: TextView = root.findViewById(R.id.text_dashboard)
    dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }*/



}