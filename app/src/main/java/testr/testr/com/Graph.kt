package testr.testr.com

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries




class Graph : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        val graph = findViewById<View>(R.id.graph) as GraphView
        graph.viewport.isScalable = true
        graph.viewport.isScrollable = true

        // github: jingxue95

        val series = LineGraphSeries(arrayOf(
                DataPoint(0.0, 3.0),
                DataPoint(1.0, 3.0),
                DataPoint(2.0, 6.0),
                DataPoint(3.0, 2.0),
                DataPoint(4.0, 5.0)))
        graph.addSeries(series)

        var avgMark = 0
        var totalMark = 0
        var avgPercentage = 0
        var median = 0

        val average = findViewById<View>(R.id.average) as TextView
        average.text = "Average: " + avgMark + "/" + totalMark + " (" + avgPercentage + "%)" + "     Median: " + median
    }
}
