//package com.example.myapplication.ui.dashboard.preference
//
//import android.app.Dialog
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.DialogFragment
//import androidx.appcompat.app.AlertDialog
//import android.widget.NumberPicker
//import androidx.lifecycle.ViewModelProvider
//import com.example.myapplication.R
//import com.example.myapplication.ui.network.model.BaseDictModel
//import com.google.gson.Gson
//
//class DynamicTwoColumnWheelDialogFragmentBakup : DialogFragment() {
//
//    private lateinit var numberPicker1: NumberPicker
//    private lateinit var numberPicker2: NumberPicker
//    private var selectedValue1: String = ""
//    private var selectedValue2: String = ""
//    private lateinit var dynamicTwoColumnWheelDialogViewModel: DynamicTwoColumnWheelDialogViewModel
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val builder = AlertDialog.Builder(requireActivity())
////        val dialog = BottomSheetDialog(context)
//
//        val inflater = requireActivity().layoutInflater
//        val dialogView = inflater.inflate(R.layout.dialog_two_column_wheel, null)
//        builder.setView(dialogView)
//
//        dynamicTwoColumnWheelDialogViewModel =
//            ViewModelProvider(this)[DynamicTwoColumnWheelDialogViewModel::class.java]
//
//        numberPicker1 = dialogView.findViewById(R.id.numberPicker1)
//        numberPicker2 = dialogView.findViewById(R.id.numberPicker2)
//
//        //初始化数据需要加缓存，减少字典表的请求流量
//        var picker1DataList = mutableListOf<BaseDictModel>() // Initialize with an empty list
//        var mutableMap = mutableMapOf<String?, List<BaseDictModel>?>()
//        var mutableMapModel = mutableMapOf<String?, BaseDictModel>()
//        dynamicTwoColumnWheelDialogViewModel.initData()
//        dynamicTwoColumnWheelDialogViewModel.dataList.observe(this){
//            var childs = it[1].child
//            if (childs != null) {
//                it[0].id = "1"
//                picker1DataList = mutableListOf(it[0])
//                picker1DataList.addAll(childs)
//                childs.forEach {
//                    r ->
//                    mutableMap = mutableMapOf(r.id to r.child)
//                    mutableMapModel = mutableMapOf(r.id to r)
//                }
//            }
//
//            //init wheel
//            val values1 = picker1DataList.map{r -> r.dictItemName}.toTypedArray()
//            val values2 = mutableMap["14"]?.map { r-> r.dictItemName}?.toTypedArray()
//
//            Log.i(tag, Gson().toJson(values1))
//            numberPicker1.minValue = 1
//            numberPicker1.maxValue = values1.size
//            numberPicker1.value = 14
//            numberPicker1.displayedValues = values1
//
//            if (values2 != null) {
//                numberPicker2.minValue = 1
//                numberPicker2.maxValue = values2.size
//                numberPicker2.value = 14
//                numberPicker2.displayedValues = values2
//            }
//
//            // 设置第一列的监听器，在值变化时更新第二列的值
//            numberPicker1.setOnValueChangedListener { _, _, newVal ->
//                selectedValue1 = values1[newVal].toString()
//                var baseDictModels = mutableMap[newVal.toString()]?.map { r-> r.dictItemName}?.toTypedArray()
//                updateSecondColumnValues(baseDictModels)
//            }
//
//            // 初始化第二列的值
////        updateSecondColumnValues(1)
//
//            builder.setTitle("Pick Values")
//                .setPositiveButton("OK") { _, _ ->
//                    selectedValue1 = values1[numberPicker1.value].toString()
//                    selectedValue2 = values2?.get(numberPicker2.value) ?: ""
//                    // 执行选中值的操作
//                    // 例如，可以将选中的值回传给调用者
//                    val resultBundle = Bundle().apply {
//                        putString("value1", selectedValue1)
//                        putString("value2", selectedValue2)
//                    }
//                    parentFragmentManager.setFragmentResult("twoColumnResult", resultBundle)
//                    dismiss()
//                }
//                .setNegativeButton("Cancel") { dialog, _ ->
//                    dialog.cancel()
//                }
//        }
//
//        return builder.create()
//    }
//
//    private fun updateSecondColumnValues(values2 : Array<String?>?) {
//        numberPicker2.minValue = 1
//        if (values2 != null) {
//            numberPicker2.maxValue = values2.size
//        }
//        numberPicker2.displayedValues = values2
//        selectedValue2 = values2?.get(numberPicker2.value) ?: ""
//    }
//
//}
