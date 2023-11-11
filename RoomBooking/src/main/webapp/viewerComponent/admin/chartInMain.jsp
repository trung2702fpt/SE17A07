<div class="row">
    <!-- Area Chart -->
    <div class="card col-xl-12 col-lg-12">
        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
            <h3 class="card-title">
                <i class="fas fa-chart-pie mr-1"></i>
                Statis
            </h3>
            <div class="card-tools">
                <ul class="nav nav-pills ml-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#revenue-chart" data-toggle="tab">A Year</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#sales-chart" data-toggle="tab">Cancelation by month</a>
                    </li>
                </ul>
            </div>
        </div><!-- /.card-header -->
        <div class="card-body">
            <div class="tab-content p-0">
                <!-- Morris chart - Sales -->
                <div class="chart tab-pane active" id="revenue-chart"
                     style="position: relative;">
                    <label for="yearSelect" >Year: </label>
                    <select name="yearToStatic" class="form-select selected" id="yearSelect">
                        <option value="value">years</option>
                    </select>
                    <div class="chart-area">
                        <canvas id="myAreaChart"></canvas>
                    </div>
                </div>
                <div class="chart tab-pane" id="sales-chart" style="position: relative; height: 300px;">
                    <canvas id="cancelationChart" height="300" style="height: 300px;"></canvas>
                </div>
            </div>
        </div><!-- /.card-body -->
    </div>
</div>