/*******************************************************************************
 * Copyright 2016 Adobe Systems Incorporated
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package we.retail.core.util;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.api.Page;

public class WeRetailHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeRetailHelper.class);
    private static final String PROP_NAV_ROOT = "navRoot";

    /**
     * Returns the root page of the site
     * E.g.: /content/we-retail/us/en
     * @param resourcePage  the current Page
     * @return root page
     */
    public static Page findRoot(Page resourcePage) {
        Page rootPage = resourcePage;
        while(rootPage != null && !isRoot(rootPage)) {
            rootPage = rootPage.getParent();
        }
        return rootPage;
    }

    /**
     * Checks if a page is the root page of the site
     * @param page current page to check
     * @return
     */
    public static boolean isRoot(Page page) {
        Resource res = page.getContentResource();
        ValueMap vm = res.adaptTo(ValueMap.class);
        return vm.get(PROP_NAV_ROOT, false);
    }

    /**
     * Tells if a string is empty or contains only white space characters
     * (characters with a code greater than '\u0020').
     * @param str   The string to be checked
     * @return      <code>true</code> if the string is null, or contains only whitespace characters.
     *              <code>false</code> otherwise.
     */
    public static boolean isEmpty(final String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * Tells if a string is not empty and contains other characters than white spaces
     * (characters with a code greater than '\u0020').
     * @param str   The string to be checked.
     * @return      <code>true</code> if the string is not null, and contains non-whitespace characters.
     *              <code>false</code> otherwise.
     */
    public static boolean notEmpty(final String str) {
        return (str != null && str.trim().length() > 0);
    }

    /**
     * Returns the title of the given resource. If the title is empty it will fallback to the page title, title,
     * or name of the given page.
     * @param resource  The resource.
     * @param page      The page to fallback to.
     * @return          The best suited title found (or <code>null</code> if resource is <code>null</code>).
     */
    public static String getTitle(final Resource resource, final Page page) {
        if (resource != null) {
            final ValueMap properties = resource.adaptTo(ValueMap.class);
            if (properties != null) {
                final String title = properties.get(NameConstants.PN_TITLE, String.class);
                if (notEmpty(title)) {
                    return title;
                } else {
                    return getPageTitle(page);
                }
            }
        } else {
            LOGGER.debug("Provided resource argument is null");
        }
        return null;
    }

    /**
     * Returns the page title of the given page. If the page title is empty it will fallback to the title and to the
     * name of the page.
     * @param page  The page.
     * @return      The best suited title found (or <code>null</code> if page is <code>null</code>).
     */
    public static String getPageTitle(final Page page) {
        if (page != null) {
            final String title = page.getPageTitle();
            if (isEmpty(title)) {
                return WeRetailHelper.getTitle(page);
            }
            return title;
        } else {
            LOGGER.debug("Provided page argument is null");
            return null;
        }
    }

    /**
     * Returns the title of the given page. If the title is empty it will fallback to the name of the page.
     * @param page  The page.
     * @return      The best suited title found (or <code>null</code> if page is <code>null</code>).
     */
    public static String getTitle(final Page page) {
        if (page != null) {
            final String title = page.getTitle();
            if (isEmpty(title)) {
                return page.getName();
            }
            return title;
        } else {
            LOGGER.debug("Provided page argument is null");
            return null;
        }
    }
}
