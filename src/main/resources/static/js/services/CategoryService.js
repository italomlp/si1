angular.module('todoList').factory('Category', function ($resource) {
    return $resource('/categories/:categoryId');
}).factory('CategoryScope', function (Category, toastr) {

    var categoryScope = {};

    categoryScope.getCategories = function () {
        return Category.query(
            function (categories) {
               return categories;
            },
            function (error) {
                console.log(error);
                toastr.error('Não foi possível obter categorias.', 'Erro');
            }
        )
    };

    categoryScope.removeCategory = function (id) {
        return Category.delete({categoryId: id},
                function () {
                    return getCategories();
                },
                function (error) {
                    toastr.error('Não foi possível remover caregoria.');
                    console.log(error);
                }
        )
    };

    categoryScope.getOneCategory = function (id) {
        if (id) {
            return Category.get({categoryId: id},
                function (category) {
                    return category;
                },
                function (error) {
                    toastr.error('Não foi possível obter lista', 'Erro');
                    console.log(error);
                }
            )
        } else {
            return new Category();
        }
    };

    categoryScope.saveCategory = function (category) {
        category.$save()
            .then(function () {
                toastr.success('Salvo com sucesso.', 'Sucesso');
            })
            .catch(function (error) {
                toastr.error('Não foi possível salvar.', 'Erro');
                console.log(error);
            });
    };

    return categoryScope;

});