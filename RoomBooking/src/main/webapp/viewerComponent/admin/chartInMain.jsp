<div class="row">
    <!-- Area Chart -->
    <div class="card col-xl-12 col-lg-12">
        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
            <h3 class="card-title">
                <i class="fas fa-chart-pie mr-1"></i>
                Statis
            </h3>
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
            </div>
        </div><!-- /.card-body -->
    </div>
</div>