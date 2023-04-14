package io.lostImagin4tion.financialHelper.ui.components.charts

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.legend.verticalLegendItem
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.axis.horizontal.HorizontalAxis
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.chart.edges.FadingEdges
import com.patrykandpatrick.vico.core.chart.scale.AutoScaleUp
import com.patrykandpatrick.vico.core.component.Component
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.context.DrawContext
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import io.lostImagin4tion.financialHelper.domain.entities.ui.CustomChartEntry
import io.lostImagin4tion.financialHelper.domain.entities.ui.LineChartEntity
import io.lostImagin4tion.financialHelper.ui.theme.finHelperLightGray

@Composable
fun CustomLineChart(
    lineModel: LineChartEntity,
    modifier: Modifier = Modifier,
) {
    val chart = lineChart(
        lines = listOf(
            lineSpec(
                lineColor = lineModel.lineColor,
                lineBackgroundShader = verticalGradient(
                    colors = arrayOf(
                        lineModel.lineColor.copy(alpha = 0.5f),
                        lineModel.lineColor.copy(alpha = 0f),
                    )
                )
            )
        )
    )
    val entries = MutableList(lineModel.points.size) { i ->
        lineModel.points[i].let {
            CustomChartEntry(
                date = it.date,
                x = i.toFloat(),
                y = it.sum
            )
        }
    }
    val model = ChartEntryModelProducer(entries).getModel()

    val legendItems = listOf(
        verticalLegendItem(
            icon = shapeComponent(
                Shapes.pillShape,
                lineModel.lineColor
            ),
            label = textComponent(
                color = currentChartStyle.axis.axisLabelColor,
                textSize = 14.sp,
                typeface = Typeface.DEFAULT,
            ),
            labelText = stringResource(lineModel.lineLegendRes),
        )
    )

    val horizontalAxisFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { index, chartValues ->
            (chartValues.chartEntryModel.entries.first().getOrNull(index.toInt()) as? CustomChartEntry)
                ?.date
                .orEmpty()
        }

    Chart(
        chart = chart,
        autoScaleUp = AutoScaleUp.Full,
        model = model,
        fadingEdges = FadingEdges(),
        startAxis = VerticalAxis(AxisPosition.Vertical.Start)
            .apply {
                this.label = TextComponent.Builder()
                    .apply {
                        color = MaterialTheme.colorScheme.primary.toArgb()
                        margins = MutableDimensions(
                            horizontalDp = 4f,
                            verticalDp = 0f
                        )
                    }
                    .build()

                this.maxLabelCount = 5

                this.axisLine = LineComponent(
                    color = MaterialTheme.colorScheme.primary.toArgb(),
                    thicknessDp = 2f
                )

                this.tick = LineComponent(
                    color = MaterialTheme.colorScheme.primary.toArgb(),
                    thicknessDp = 2f
                )
                this.tickLengthDp = 5f

                this.horizontalLabelPosition = VerticalAxis.HorizontalLabelPosition.Outside
                this.guideline = LineComponent(
                    color = finHelperLightGray.toArgb(),
                    thicknessDp = 1f
                )
            },
        bottomAxis = HorizontalAxis(AxisPosition.Horizontal.Bottom)
            .apply {
                this.valueFormatter = horizontalAxisFormatter
                this.label = TextComponent.Builder()
                    .apply {
                        color = MaterialTheme.colorScheme.primary.toArgb()
                        margins = MutableDimensions(
                            horizontalDp = 0f,
                            verticalDp = 2f
                        )
                    }
                    .build()

                this.axisLine = LineComponent(
                    color = MaterialTheme.colorScheme.primary.toArgb(),
                    thicknessDp = 2f
                )

                this.tick = LineComponent(
                    color = MaterialTheme.colorScheme.primary.toArgb(),
                    thicknessDp = 2f
                )
                this.tickLengthDp = 5f

                this.guideline = LineComponent(
                    color = finHelperLightGray.toArgb(),
                    thicknessDp = 1f
                )
                this.tickPosition = HorizontalAxis.TickPosition.Center()
            },
        marker = MarkerComponent(
            label = TextComponent.Builder()
                .apply {
                    this.color = MaterialTheme.colorScheme.primary.toArgb()
                }
                .build(),
            indicator = object : Component() {
                override fun draw(
                    context: DrawContext,
                    left: Float,
                    top: Float,
                    right: Float,
                    bottom: Float
                ) {
                    context.canvas.drawCircle(
                        (right + left) / 2,
                        (top + bottom) / 2,
                        20f,
                        Paint().apply { color = Color.Blue.toArgb() }
                    )
                }
            },
            guideline = null
        ),
        legend = verticalLegend(
            items = legendItems,
            iconSize = 12.dp,
            iconPadding = 10.dp,
            spacing = 5.dp,
            padding = MutableDimensions(
                verticalDp = 10f,
                horizontalDp = -25f
            )
        ),
        modifier = modifier
    )
}
