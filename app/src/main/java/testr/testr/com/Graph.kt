package testr.testr.com

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*
import kotlin.collections.ArrayList


class Graph : AppCompatActivity(), Observer {

    private lateinit var mModel: Model

    override fun update(p0: Observable?, p1: Any?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        mModel = Model.getInstance()
        mModel.addObserver(this)

        mModel.initObservers()

        val graph = findViewById<View>(R.id.graph) as GraphView
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinY(0.0)
        graph.viewport.setMinX(0.0)


        val map = HashMap<Int, Int>()

        for (item in mModel.sortedStudentScore) {
            if (map.containsKey(item)) {
                map[item] = map[item]!!.plus(1)
            } else {
                map[item] = 1
            }
        }

        var dataPoints = arrayListOf<DataPoint>()

        Log.e("key", "here")

        for (item in map) {
            dataPoints.add(DataPoint(item.key.toDouble(), item.value.toDouble()))
            Log.e("key", item.key.toString() + " " + item.value.toString())
        }

        val series = BarGraphSeries<DataPoint>(dataPoints.toTypedArray())
        graph.addSeries(series)

        var avgMark = mModel.average
        var totalMark = mModel.correctAnswerSet.size
        var avgPercentage = avgMark.toDouble() * 100.00 / mModel.correctAnswerSet.size.toDouble()
        var marks = mModel.studentScore
        var median = 0.0
        if (marks.size.rem(2) == 0) {
            median = (mModel.sortedStudentScore[marks.size / 2].toDouble() + mModel.sortedStudentScore[marks.size / 2 - 1].toDouble())/2.toDouble()
        } else {
            median = (mModel.sortedStudentScore)[marks.size / 2].toDouble()
        }

        val average = findViewById<View>(R.id.average) as TextView
        average.text = "Average: " + avgMark.toString() + "/" + totalMark + " (" + avgPercentage + "%)" + "     Median: " + median
    }
}
