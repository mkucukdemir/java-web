/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$.getJSON("ajax/viewdashboard", function (response) {
    
    // generate charts - sparklines and line graphs
    $.each( response.monthly, function( key, category ) {
        if(key%3 === 0)
            $('<div class="row sparkboxes mt-4 mb-4"></div>').appendTo('div.main');
        $('<div class="col-md-4"><div class="box box'+(key+1)+'"><div id="spark'+key+'"></div></div></div>').appendTo('div.main div.row:last-child');
        
        new ApexCharts(document.querySelector("#spark"+key), {
            chart: {
                id: 'sparkline'+key,
                group: 'sparklines',
                type: 'area',
                height: 160,
                sparkline: {
                    enabled: true
                },
            },
            stroke: {
                curve: 'straight'
            },
            fill: {
                opacity: 1,
            },
            series: [],
            noData: {
                text: 'Loading...'
            },
            yaxis: {
                min: 0,
                labels: {
                    formatter: function (value) {
                        return new Intl.NumberFormat('tr-TR', { style: 'currency', currency: 'TRY' }).format(value/100);
                    }
                }
            },
            xaxis: {
                type: 'datetime',
            },
            colors: ['#008FFB'],
            title: {
                text: '$424,652',
                offsetX: 30,
                style: {
                    fontSize: '18px',
                    cssClass: 'apexcharts-yaxis-title'
                }
            },
            subtitle: {
                text: 'Tutarı',
                offsetX: 30,
                style: {
                    fontSize: '14px',
                    cssClass: 'apexcharts-yaxis-title'
                }
            },
            tooltip: {
                x: {
                    show: true,
                    format: 'MMM yyyy'
                }
            }
        }).render();
    });
    
    $('<div class="row mt-4 mb-4"></div>').appendTo('div.main');
    $('<div class="col-md-6"><div class="box"><div id="line_daily"></div></div></div>').appendTo('div.main div.row:last-child');
    $('<div class="col-md-6"><div class="box"><div id="line_monthly"></div></div></div>').appendTo('div.main div.row:last-child');
    
    $('<div class="row mt-4 mb-4"></div>').appendTo('div.main');
    $('<div class="col-md-6"><div class="box"><div id="line_yearly"></div></div></div>').appendTo('div.main div.row:last-child');
    
    var optionsLineDaily = {
        series: [],
        chart: {
            type: 'area',
            stacked: false,
            height: 350,
            zoom: {
                type: 'x',
                enabled: true,
                autoScaleYaxis: true
            },
            toolbar: {
                autoSelected: 'zoom'
            }
        },
        dataLabels: {
            enable: false
        },
        markers: {
            size: 0
        },
        title: {
            text: 'Account Activities',
            align: 'left'
        },
        fill: {
            type: 'gradient',
            gradient: {
                shadeIntensity: 1,
                inverseColors: false,
                opacityFrom: 0.5,
                opacityTo: 0.0,
                stops: [0, 90, 100]
            }
        },
        yaxis: {
            labels: {
                formatter: function (value) {
                    return new Intl.NumberFormat('tr-TR', { style: 'currency', currency: 'TRY' }).format(value/100);
                }
            },
            title: {
                text: 'Amount(TL/d)'
            }
        },
        xaxis: {
            type: 'datetime'
        },
        tooltip: {
            shared: false,
            y: {
                formatter: function (val) {
                    return (val / 1000000).toFixed(0)
                }
            },
            x: {
                show: true,
                format: 'dd MMM yyyy'
            }
        },
        noData: {
            text: 'Loading...'
        }
    }

    var optionsLineMonthly = {
        series: [],
        chart: {
            type: 'area',
            stacked: false,
            height: 350,
            zoom: {
                type: 'x',
                enabled: true,
                autoScaleYaxis: true
            },
            toolbar: {
                autoSelected: 'zoom'
            }
        },
        dataLabels: {
            enable: false
        },
        markers: {
            size: 0
        },
        title: {
            text: 'Account Activities',
            align: 'left'
        },
        fill: {
            type: 'gradient',
            gradient: {
                shadeIntensity: 1,
                inverseColors: false,
                opacityFrom: 0.5,
                opacityTo: 0.0,
                stops: [0, 90, 100]
            }
        },
        yaxis: {
            labels: {
                formatter: function (value) {
                    return new Intl.NumberFormat('tr-TR', { style: 'currency', currency: 'TRY' }).format(value/100);
                }
            },
            title: {
                text: 'Amount(TL/M)'
            }
        },
        xaxis: {
            type: 'datetime'
        },
        tooltip: {
            shared: false,
            x: {
                show: true,
                format: 'MMM yyyy'
            }
        },
        noData: {
            text: 'Loading...'
        }
    }

    var optionsLineYearly = {
        series: [],
        chart: {
            type: 'area',
            stacked: false,
            height: 350,
            zoom: {
                type: 'x',
                enabled: true,
                autoScaleYaxis: true
            },
            toolbar: {
                autoSelected: 'zoom'
            }
        },
        dataLabels: {
            enable: false
        },
        markers: {
            size: 0
        },
        title: {
            text: 'Account Activities',
            align: 'left'
        },
        fill: {
            type: 'gradient',
            gradient: {
                shadeIntensity: 1,
                inverseColors: false,
                opacityFrom: 0.5,
                opacityTo: 0.0,
                stops: [0, 90, 100]
            }
        },
        yaxis: {
            labels: {
                formatter: function (value) {
                    return new Intl.NumberFormat('tr-TR', { style: 'currency', currency: 'TRY' }).format(value/100);
                }
            },
            title: {
                text: 'Amount(TL/Y)'
            }
        },
        xaxis: {
            type: 'datetime'
        },
        tooltip: {
            shared: false,
            x: {
                show: true,
                format: 'yyyy'
            }
        },
        noData: {
            text: 'Loading...'
        }
    }

    var chartLineDaily = new ApexCharts(document.querySelector('#line_daily'), optionsLineDaily);
    chartLineDaily.render();

    var chartLineMonthly = new ApexCharts(document.querySelector('#line_monthly'), optionsLineMonthly);
    chartLineMonthly.render();

    var chartLineYearly = new ApexCharts(document.querySelector('#line_yearly'), optionsLineYearly);

    // a small hack to extend height in website sample dashboard
    chartLineYearly.render().then(function () {
        var ifr = document.querySelector("#wrapper");
        if (ifr.contentDocument) {
            ifr.style.height = ifr.contentDocument.body.scrollHeight + 20 + 'px';
        }
    });

    $.each( response.monthly, function( key, category ) {
        ApexCharts.exec('sparkline' + key, 'updateSeries', new Array(category), true);
        ApexCharts.exec('sparkline' + key, 'updateOptions', {title: {text: category.name}}, true);
    });
    chartLineDaily.updateSeries(response.daily);
    chartLineMonthly.updateSeries(response.monthly);
    chartLineYearly.updateSeries(response.yearly);
    
});