<div id="page-wrapper" ng-controller="AdminEmployeeCtrl">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12"><br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <i class="fa fa-users fa-fw"></i> Employee list
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-8">
                                <div class="input-group">
                                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                                            data-target="#newEmpModal">New Employee
                                    </button>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="input-group pull-right">
                                    <div class="input-group-addon"><i class="fa fa-search"></i></div>
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                            </div>
                        </div>
                        <br>
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Birthday</th>
                                    <th>Hire Date</th>
                                    <th>Salary</th>
                                    <th>Gender</th>
                                    <th>Email</th>
                                    <th>Projects</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                    <th>Profile</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <button type="button" class="btn btn-warning btn-xs" data-toggle="modal"
                                                data-target="#updEmpModal">Update</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-xs">Delete</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-xs">Profile</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="col-lg-2">
                                <div class="form-group">
                                    <label>
                                        <select class="form-control" ng-model="tableSize">
                                            <option>5</option>
                                            <option>10</option>
                                            <option>25</option>
                                            <option>50</option>
                                            <option>100</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
        <!-- New Employee Modal -->
        <div class="modal fade" id="newEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Add new Employee</h4>
                    </div>
                    <div class="modal-body">
                        <form id="newEmpForm" name="newEmpForm">
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="firstName">First name:</label>
                                    <input type="text" class="form-control" id="firstName" name="firstName" pattern="/^[a-zA-Z]{2,10}$/" required/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="lastName">Last name:</label>
                                    <input type="text" class="form-control" id="lastName" name="lastName" pattern="/^[a-zA-Z]{2,10}$/" required/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-3">
                                    <label for="salary">Salary:</label>
                                    <input type="text" class="form-control" id="salary" name="salary" required/>
                                </div>
                                <div class="form-group form-group-sm col-md-3">
                                    <label for="gender">Gender:</label>
                                    <select class="form-control custom-select-height" id="gender" name="gender" required>
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" id="email" name="email" required/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="birthday">Birthday:</label>
                                    <input type="date" class="form-control" id="birthday" name="birthday" required/>
                                </div>

                                <div class="form-group col-lg-6">
                                    <label for="hiredate">Hiredate:</label>
                                    <input type="date" class="form-control" id="hiredate" name="hiredate" required/>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group form-group-sm col-md-6" id="npm">
                                    <label for="npm">Assign as PM for the project:</label>
                                    <select class="form-control custom-select-height" required>
                                        <option>Minerva</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Save changes</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Update Employee Modal -->
        <div class="modal fade" id="updEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #337AB7">
                        <h4 class="modal-title">Update Employee</h4>
                    </div>
                    <div class="modal-body">
                        <form name="updEmpForm" novalidate>
                            <div class="col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="ufirstName">First name:</label>
                                    <input type="text" class="form-control" id="ufirstName" pattern="/^[a-zA-Z]{2,10}$/" required/>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="ulastName">Last name:</label>
                                    <input type="text" class="form-control" pattern="/^[a-zA-Z]{2,10}$/" id="ulastName" required>
                                </div>
                            </div>
                            <div class="col-lg-12 outer">
                                <div class="form-group col-lg-3">
                                    <label for="usalary">Salary:</label>
                                    <input type="text" class="form-control" id="usalary" pattern="/^[0-9]+$/" required>
                                </div>
                                <div class="form-group form-group-sm col-md-3">
                                    <label for="ugender">Gender:</label>
                                    <select class="form-control custom-select-height" id="ugender" required>
                                        <option>Male</option>
                                        <option>Female</option>
                                    </select>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="uemail">Email:</label>
                                    <input type="email" class="form-control" id="uemail" required>
                                </div>
                            </div>
                            <div class="col-lg-12 outer">
                                <div class="form-group col-lg-6">
                                    <label for="ubirthday">Birthday:</label>
                                    <input type="date" class="form-control" id="ubirthday" required>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label for="uhiredate">Hiredate:</label>
                                    <input type="date" class="form-control" id="uhiredate" required>
                                </div>
                            </div>
                            <div class="form-group col-lg-12 outer">
                                <div class="form-group form-group-sm col-md-6" id="upm">
                                    <label for="upm">Assign as PM for the project:</label>
                                    <select class="form-control custom-select-height" required>
                                        <option>Minerva</option>
                                    </select>
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Save changes</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close menu</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>