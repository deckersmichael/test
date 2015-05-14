AmCharts.makeChart("changesChart",
    {
        "type": "serial",
        "path": "http://www.amcharts.com/lib/3/",
        "categoryField": "category",
        "autoMarginOffset": 40,
        "marginRight": 60,
        "marginTop": 60,
        "startDuration": 1,
        "fontSize": 13,
        "theme": "light",
        "categoryAxis": {
            "autoRotateAngle": -90,
            "autoRotateCount": 0,
            "gridPosition": "start",
            "fontSize": 9,
            "labelRotation": -90
        },
        "chartScrollbar": {
            "dragIcon": "dragIconRectBig"
        },
        "trendLines": [],
        "graphs": [
            {
                "balloonText": "[[title]] of [[category]]:[[value]]",
                "bullet": "round",
                "bulletBorderThickness": 0,
                "bulletSize": 0,
                "id": "AmGraph-1",
                "labelOffset": 1,
                "lineAlpha": 1,
                "lineThickness": 2,
                "title": "graph 1",
                "type": "smoothedLine",
                "valueField": "column-1"
            },
            {
                "id": "AmGraph-2",
                "labelRotation": 162,
                "title": "graph 2",
                "valueField": "column-2"
            }
        ],
        "guides": [],
        "valueAxes": [
            {
                "id": "ValueAxis-1",
                "title": ""
            }
        ],
        "allLabels": [],
        "balloon": {},
        "titles": [],
        "dataProvider": window.changesJSON
    }
);