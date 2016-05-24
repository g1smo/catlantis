(ns catlantis.ios.screens.categories
  (:require [catlantis.shared.ui :as ui]
            [re-frame.core :as rf]
            [print.foo :as pf :include-macros true]))

(declare styles)

(defn on-categ-press [ctg]
  (rf/dispatch [:category-select ctg])
  (rf/dispatch [:nav/toggle-drawer]))

(defn categories []
  (let [catgs (rf/subscribe [:categories])
        {:keys [container]} styles]
    [ui/view
     {:style container}
     [ui/text {:style (:title styles)} "Categories"]
     (for [{:keys [id name] :as ctg} @catgs]
       [ui/list-item {:text           (if id name "All")
                      :style          (:list-item styles)
                      :style-text     (:list-item-text styles)
                      :on-press       (partial on-categ-press ctg)
                      :key            id
                      :underlay-color (ui/color :grey300)}])]))

(def styles
  (ui/create-stylesheet
    {:container      {:flex        1
                      :padding-top 40}
     :list-item      {:flex 1}
     :list-item-text {:text-align :center}
     :title          {:text-align     :center
                      :padding-bottom 20
                      :font-size      20
                      :font-weight    "500"}}))