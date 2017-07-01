(ns reagent-tutorial.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "This text is printed from src/reagent-tutorial/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(def data [{:author "Pete Hunt", :text "This is one comment"}
           {:author "Jordan Walke", :text "This is *another* comment"}])


(defn comment-item [author & children]
  (into [:div.comment
         [:h2.commentAuthor author ]] children))

(defn comment-list [data]
  [:div.commentList
   (for [comment @data]
   [comment-item (:author comment) (:text comment)])])

(defn comment-form []
  [:div.commentForm
   "Hello, world! I am a CommentForm"
   ])

(defn comment-box []
  (let [data (atom data)]
  ;; add an ajax callback here
  (fn []
  [:div.commentBox
   [:h1 "Comments"]
   [comment-list data]
   [comment-form]])))

(defn mount-root []
 (reagent/render-component [comment-box data]
                           (.getElementById js/document "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
