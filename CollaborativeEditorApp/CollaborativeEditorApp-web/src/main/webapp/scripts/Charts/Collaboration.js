AmCharts.makeChart("collabChart",
    {
        "type": "pie",
        "path": "http://www.amcharts.com/lib/3/",
        "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
        "titleField": "category",
        "valueField": "column-1",
        "theme": "light",
        "allLabels": [],
        "balloon": {},
        "legend": {
            "align": "center",
            "markerType": "circle"
        },
        "titles": [],
        "dataProvider": window.collabJSON
    }
);

