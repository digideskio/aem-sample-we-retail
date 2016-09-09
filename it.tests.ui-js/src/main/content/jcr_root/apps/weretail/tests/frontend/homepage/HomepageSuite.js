/*
 *  Copyright 2016 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
;(function(h,$){
    var PRODUCT_GRID_CLASS = ".productgrid";

    window.CQ.WeRetailIT.HomepageLoadTest = function (h, $) {

        return new h.TestCase("Load homepage")
            .navigateTo("/content/we-retail/us/en.html")
            .asserts.location("/content/we-retail/us/en.html", true);
    }

    new h.TestSuite("We.Retail Tests - Homepage", {path:"/apps/weretail/tests/homepage/HomepageSuite.js", register: true})
        .addTestCase(window.CQ.WeRetailIT.HomepageLoadTest(h, $))
        .addTestCase(window.CQ.WeRetailIT.NavbarTest(h, $, 5))
        .addTestCase(window.CQ.WeRetailIT.HeroImageTest(h, $))
        .addTestCase(window.CQ.WeRetailIT.TeasersTest(h, $, 6))
        .addTestCase(window.CQ.WeRetailIT.SiteFeaturesTest(h, $))
        // Test featured products
        .addTestCase(window.CQ.WeRetailIT.ProductsGridTest(h, $, PRODUCT_GRID_CLASS + ":first", 6))
        .addTestCase(window.CQ.WeRetailIT.ProductTest(h, $, PRODUCT_GRID_CLASS + ":first .we-ProductsGrid-item:first"))
        .addTestCase(window.CQ.WeRetailIT.ArticlesTest(h, $, 6))
        // Test new arrivals products
        .addTestCase(window.CQ.WeRetailIT.ProductsGridTest(h, $, PRODUCT_GRID_CLASS + ":eq(1)", 6))
        .addTestCase(window.CQ.WeRetailIT.ProductTest(h, $, PRODUCT_GRID_CLASS + ":eq(1) .we-ProductsGrid-item:first"))
        .addTestCase(window.CQ.WeRetailIT.FooterTest(h, $));
})(hobs, jQuery);
