$(document).ready(() => {
    Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
    Chart.defaults.global.defaultFontColor = '#858796';

    var yearSelect = document.getElementById("yearSelect");

    var currentYear = new Date().getFullYear();
    yearSelect.innerHTML = "";
    for (var i = 0; i < 5; i++) {
        var option = document.createElement("option");
        option.text = currentYear - i;
        option.value = currentYear - i;
        yearSelect.appendChild(option);
    }

});

function number_format(number, decimals, dec_point, thousands_sep) {
    // *     example: number_format(1234.56, 2, ',', ' ');
    // *     return: '1 234,56'
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
            s = '',
            toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.round(n * k) / k;
            };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

var labels = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

var layoutChart = {
    padding: {
        left: 10,
        right: 25,
        top: 25,
        bottom: 0
    }
}

var scaleChart = {
    xAxes: [{
            time: {
                unit: 'date'
            },
            gridLines: {
                display: false,
                drawBorder: false
            },
            ticks: {
                maxTicksLimit: 7
            }
        }],
    yAxes: [{
            ticks: {
                maxTicksLimit: 5,
                padding: 10,
                // Include a dollar sign in the ticks
                callback: function (value, index, values) {
                    return number_format(value);
                }
            },
            gridLines: {
                color: "rgb(234, 236, 244)",
                zeroLineColor: "rgb(234, 236, 244)",
                drawBorder: false,
                borderDash: [2],
                zeroLineBorderDash: [2]
            }
        }],
};

var toolTipChart = {backgroundColor: "rgb(255,255,255)",
    bodyFontColor: "#858796",
    titleMarginBottom: 10,
    titleFontColor: '#6e707e',
    titleFontSize: 14,
    borderColor: '#dddfeb',
    borderWidth: 1,
    xPadding: 15,
    yPadding: 15,
    displayColors: false,
    intersect: false,
    mode: 'index',
    caretPadding: 10, }

function setChartBookingAndCanel(data) {
    console.log(data);
    var booked = new Array();
    var cancel = new Array();
    var cancelpersent = new Array();
    for (var i = 0, max = 12; i < max; i++) {
        booked.push(data[i].booing);
        cancel.push(data[i].cancel);
        cancelpersent.push(data[i].persentCancelation);
    }
    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                    label: "Booked",
                    lineTension: 0.3,
                    backgroundColor: "rgba(78, 115, 223, 0.05)",
                    borderColor: "rgba(78, 115, 223, 1)",
                    pointRadius: 3,
                    pointBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointBorderColor: "rgba(78, 115, 223, 1)",
                    pointHoverRadius: 3,
                    pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
                    data: booked,
                }, {
                    label: "Cancelation",
                    lineTension: 0.3,
                    backgroundColor: "rgba(255, 0, 0, 0.05)",
                    borderColor: "rgba(255, 0, 0, 1)",
                    pointRadius: 3,
                    pointBackgroundColor: "rgba(255, 0, 0, 1)",
                    pointBorderColor: "rgba(255, 0, 0, 1)",
                    pointHoverRadius: 3,
                    pointHoverBackgroundColor: "rgba(255, 0, 0, 1)",
                    pointHoverBorderColor: "rgba(255, 0, 0, 1)",
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
                    data: cancel,
                }]
        },
        options: {
            maintainAspectRatio: false,
            layout: layoutChart,
            scales: scaleChart,
            legend: {
                display: false
            },
            tooltips: {
                ...toolTipChart,
                callbacks: {
                    label: function (tooltipItem, chart) {
                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                        return datasetLabel + ': ' + number_format(tooltipItem.yLabel);
                    }
                }
            }
        }
    });
    var cancelationElement = document.getElementById("cancelationChart");
    var cancelChart = new Chart(cancelationElement, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                    label: "Cancelation",
                    lineTension: 0.3,
                    backgroundColor: "rgba(255, 0, 0, 0.05)",
                    borderColor: "rgba(255, 0, 0, 1)",
                    pointRadius: 3,
                    pointBackgroundColor: "rgba(255, 0, 0, 1)",
                    pointBorderColor: "rgba(255, 0, 0, 1)",
                    pointHoverRadius: 3,
                    pointHoverBackgroundColor: "rgba(255, 0, 0, 1)",
                    pointHoverBorderColor: "rgba(255, 0, 0, 1)",
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
                    data: cancelpersent,
                }]
        },
        options: {
            maintainAspectRatio: false,
            layout: layoutChart,
            scales: scaleChart,
            legend: {
                display: false
            },
            tooltips: {
                ...toolTipChart,
                callbacks: {
                    label: function (tooltipItem, chart) {
                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                        return datasetLabel + " " + number_format(tooltipItem.yLabel) + ' %';
                    }
                }
            }
        }
    });
}

$.ajax({
    url: "/RoomBooking/StaticAdmin",
    method: "GET",
    dataType: "json",
    data: {
        action: "Chart",
    },
    success: function (data) {
        setChartBookingAndCanel(data);
    },
    error: function () {
        alert("ERROR to process call api!!");
    }
});