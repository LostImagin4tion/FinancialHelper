package io.lostImagin4tion.financialHelper.ui.components.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import io.lostImagin4tion.financialHelper.domain.entities.ui.PieChartEntity

@Composable
fun CustomPieChart(
    modifier: Modifier = Modifier,
    data: List<PieChartEntity>
) {
    val slices = data.map {
        PieChartData.Slice(
            value = it.sum,
            color = it.color
        )
    }

    PieChart(
        pieChartData = PieChartData(slices),
        animation = simpleChartAnimation(),
        sliceDrawer = SimpleSliceDrawer(
            sliceThickness = 30f
        ),
        modifier = modifier
    )
}
