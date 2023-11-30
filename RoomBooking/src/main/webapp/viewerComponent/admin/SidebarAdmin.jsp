<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="managing.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-school"></i>
        </div>
        <div class="sidebar-brand-text mx-3">FPT BOOKING</div>
    </a>
    <!-- Divider -->
    <hr class="sidebar-divider">
    <!-- Admin heading -->
    <div class="sidebar-heading text-white">
        Admin
    </div>
    <!-- Nav Item - Page Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePage"
           aria-expanded="true" aria-controls="collapsePage">
            <i class="fas fa-fw fa-inbox"></i>
            <span>Room</span>
        </a>
        <div id="collapsePage" class="collapse" aria-labelledby="headingUser" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Pages:</h6>
                <a class="collapse-item" href="viewListRoom.jsp">View List</a>
                <a class="collapse-item" href="editRoom.jsp">Edit</a>
                <a class="collapse-item" href="addRoom.jsp">Add room</a>
            </div>
        </div>
    </li>
    <!-- Nav Item - User Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUser"
           aria-expanded="true" aria-controls="collapseUser">
            <i class="fas fa-fw fa-user"></i>
            <span>Equipment</span>
        </a>
        <div id="collapseUser" class="collapse" aria-labelledby="headingUser" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Pages:</h6>
                <a class="collapse-item" href="viewListEquipments.jsp">View List</a>
                <a class="collapse-item" href="editEquipment.jsp">Edit</a>
            </div>
        </div>
    </li>
    <!-- Nav Item - Categories Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCategories"
           aria-expanded="true" aria-controls="collapseUser">
            <i class="fas fa-fw fa-list"></i>
            <span>Feedback</span>
        </a>
        <div id="collapseCategories" class="collapse" aria-labelledby="headingCategories" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Pages:</h6>
                <a class="collapse-item" href="feedback.jsp">View list</a>
                <a class="collapse-item" href="bookingManagement.jsp">Management</a>
            </div>
        </div>
    </li>
    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
<div id="messageBox"></div>
<div id="confirmBox"></div>
<div id="loadingScreen"></div>
